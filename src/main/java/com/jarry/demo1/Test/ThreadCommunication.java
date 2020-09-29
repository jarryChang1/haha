package com.jarry.demo1.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-05-26 11:56
 */
@Slf4j
public class ThreadCommunication {

    private static int sss = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void test1() throws InterruptedException {
        sss = 0;
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                log.info("thread run");
                try {
                    Thread.sleep(50);
                    countDownLatch.countDown();
                    sss = sss + 1;
//                    log.info("thread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();

        System.out.println(sss);
    }
}
