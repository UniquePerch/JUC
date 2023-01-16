package org.example.TheadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectExc implements RejectedExecutionHandler { //自定义实现Reject方法
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自己做吧");
        r.run();
    }
}
