package org.example.atomclass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
//    public static void main(String[] args) {
//        AtomicInteger i = new AtomicInteger(1);
//
//        System.out.println(i.incrementAndGet());
//        System.out.println(i.get());
//    }

    //    public static void main(String[] args) {
//        String a = "abd";
//        String b = "abc";
//        List<String> list = new CopyOnWriteArrayList<>();
//        Map<Integer, String> map = new ConcurrentHashMap<>();
//        ArrayBlockingQueue<Object> blockingDeque = new ArrayBlockingQueue<>(1);
//        list.add("add");
//        AtomicReference<String> reference = new AtomicReference<>(a);
//        reference.set(b);
//        System.out.println(reference.get());
//    }
    public static void main(String[] args) throws InterruptedException {
        BlockQue<Object> queue = new BlockQue<>(1);
        Runnable supplier = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.err.println(time() + "生产者 " + name + " 正在准备餐品...");
                    TimeUnit.SECONDS.sleep(3);
                    System.err.println(time() + "生产者 " + name + " 已出餐！");
                    queue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };
        Runnable consumer = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(time() + "消费者 " + name + " 正在等待出餐...");
                    queue.take();
                    System.out.println(time() + "消费者 " + name + " 取到了餐品。");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(time() + "消费者 " + name + " 已经将饭菜吃完了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };
        for (int i = 0; i < 2; i++) new Thread(supplier, "Supplier-" + i).start();
        for (int i = 0; i < 3; i++) new Thread(consumer, "Consumer-" + i).start();
    }

    private static String time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return "[" + format.format(new Date()) + "] ";
    }
}
