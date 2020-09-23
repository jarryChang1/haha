package com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-23 17:18
 *
 * CountDownLatch(闭锁)
 * 某个线程等待其他线程执行完毕后，它才执行(其他线程等待某个线程执行完毕后，它才执行)
 *
 * CyclicBarrier(栅栏)
 * 一组线程互相等待至某个状态，这组线程再同时执行。
 *
 * Semaphore(信号量)
 * 控制一组线程同时执行。
 */
public class CountDownLatchX {
    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("现在6点下班了.....");

        // 3y线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 这里调用的是await()不是wait()
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("...其他的5个员工走光了，3y终于可以走了");
            }
        }).start();

        // 其他员工线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("员工xxxx下班了");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }
    @Test
    public void test1(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        // 3y线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3y终于写完了");
                countDownLatch.countDown();

            }
        }).start();

        // 其他员工线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("其他员工需要等待3y");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3y终于写完了，其他员工可以开始了！");
                }
            }).start();
        }
    }
}
