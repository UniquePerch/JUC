package org.example.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        Thread t = Thread.currentThread();  //先拿到主线程的Thread对象
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("主线程可以继续运行了！");
                LockSupport.unpark(t);
                //t.interrupt();   发送中断信号也可以恢复运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("主线程被挂起！");
        LockSupport.park();
        System.out.println("主线程继续运行！");
    }
}
