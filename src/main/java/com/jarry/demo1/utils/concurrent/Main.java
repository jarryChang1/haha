package com.jarry.demo1.utils.concurrent;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.concurrent
 * @Author: Jarry.Chang
 * @CreateTime: 2020-04-27 16:41
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());

        for (int i = 100; i > 0; i--) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
            executor.submit(task);
            System.out.println(executor.getTaskCount());
        }


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 101; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, 1);
            executor.execute(task);
            System.out.println(executor.getTaskCount());
        }



        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: End of the program.\n");

    }
}