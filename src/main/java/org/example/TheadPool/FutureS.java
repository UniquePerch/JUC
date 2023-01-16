package org.example.TheadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureS {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(6);
        Future future = service.submit(() ->
                "我是字符串"
        );
        System.out.println(future.get());
        service.shutdownNow();
    }
}
