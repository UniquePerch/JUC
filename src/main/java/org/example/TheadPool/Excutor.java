package org.example.TheadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Excutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(7);
        executor.submit(() -> System.out.println(Thread.currentThread().getName() + "Hello world"));
        Thread.sleep(1000);
        executor.shutdownNow();
    }
}
