package com.jarry.demo1.java3y.ProxyMode.ThreadUse;


import java.util.concurrent.*;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-21 14:36
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = pool.submit(new Mycallable(200));
        Future<Integer> f2 = pool.submit(new Mycallable(100));

        Integer i1 = f1.get();
        Integer i2 = f2.get();

        System.out.println(i1);
        System.out.println(i2);

        pool.shutdown();
    }

    public static class Mycallable implements Callable<Integer> {//<T>T是什么，run方法就返回什么

        private int number;

        public Mycallable(int number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 1; i <= number; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
