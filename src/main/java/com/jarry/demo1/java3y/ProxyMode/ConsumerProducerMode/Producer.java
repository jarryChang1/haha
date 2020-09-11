package com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 14:17
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;

    private  final Vector sharedQueue;

    private final int SIZE;

    private static AtomicInteger count = new AtomicInteger();

    public Producer(Vector sharedQueue,int SIZE){
        this.sharedQueue = sharedQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        int data;
        Random r = new Random();

        System.out.println("start producer id = " + Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(r.nextInt(1000));
                while (sharedQueue.size() == SIZE){
                    synchronized (sharedQueue){


                    System.out.println("Queue is full, producer " + Thread.currentThread().getId()
                            + "is waiting, size: " + sharedQueue.size());
                    sharedQueue.wait();
                 }
                }

            synchronized (sharedQueue){
                data = count.incrementAndGet();
                sharedQueue.add(data);

                System.out.println("producer create data:" + data + ", size : " + sharedQueue.size());
                sharedQueue.notifyAll();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
    public void stop(){
        isRunning = false;
    }
}
