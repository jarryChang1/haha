package com.jarry.demo1.utils.util1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-25 10:06
 */
public class Threadcomu {
    private static final int MAX_NUM = 10;
    private static volatile int i = 1;
    private static final Object object = new Object();

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
//        main1();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    while (i<=MAX_NUM){
                        System.out.println(Thread.currentThread().getName()+":"+i++);
                        try {
                            object.notify();
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    object.notify();
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        for (int j = 0; j < 10; j++) {
            service.submit(()-> System.out.println(Thread.currentThread().getName()+":"+i));
        }
        t2.start();
    }
    private static void main1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                lock.lock();
                while (i <= MAX_NUM){
                    System.out.println(Thread.currentThread().getName()+":"+ i++);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    condition.signal();
                }
                }finally {
                    lock.unlock();
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
