package org.example.TheadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Schedule {
    //scheduleAtFixedRate 一开始就计算间隔时间，如果任务时间超过间隔时间，直接开始下一轮
    //scheduleAtFixedDelay 无论任务执行多久，都在任务结束后Delay相应的时间才开始下一轮
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(3);
        //ScheduledFuture<String> future = service.schedule(() -> "hello world", 3, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + "hellow"), 3, 1, TimeUnit.SECONDS);
//        System.out.println(future.get());
//        service.shutdown();
    }
}
