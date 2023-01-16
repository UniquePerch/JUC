package org.example.Utils;

import java.util.concurrent.Semaphore;

public class Semaphoree {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();   //申请一个许可证
                    System.out.println("许可证申请成功！");
                    semaphore.release();   //归还一个许可证
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
