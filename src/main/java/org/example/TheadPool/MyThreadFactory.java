package org.example.TheadPool;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    int cnt = 0;

    @Override
    public Thread newThread(Runnable r) {

        return new Thread(r, "我是自定义线程:" + cnt++);
    }
}
