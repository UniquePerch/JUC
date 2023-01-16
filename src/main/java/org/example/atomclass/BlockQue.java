package org.example.atomclass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQue<E> {
    Object[] items;

    int takeIndex = 0;

    int putIndex = 0;

    int cnt;

    final Condition notEmpty;

    final Condition notFull;

    ReentrantLock lock;

    int capacity;

    public BlockQue(int x) {
        items = new Object[x];
        this.lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        this.capacity = x;
    }

    void put(E x) throws InterruptedException {
        if (x == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (cnt == items.length)
                notFull.await();
            enque(x);
        } finally {
            lock.unlock();
        }
    }

    private void enque(E x) {
        Object[] item = this.items;
        item[putIndex] = x;
        if (++putIndex == capacity)
            putIndex = 0;
        cnt++;
        notEmpty.signal();
    }

    E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (cnt == 0)
                notEmpty.await();
            return deque();
        } finally {
            lock.unlock();
        }
    }

    private E deque() {
        final Object[] item = this.items;
        E x = (E) item[takeIndex];
        item[takeIndex] = null;
        if (++takeIndex == capacity)
            takeIndex = 0;
        this.cnt--;
        notFull.signal();
        return x;
    }
}
