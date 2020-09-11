package com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode;

import java.util.Random;
import java.util.Vector;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 14:36
 */
public class Consumer implements Runnable{

    public final Vector sharedQueue;

    public Consumer(Vector sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        Random r = new Random();

        System.out.println("start consumer id = "+ Thread.currentThread().getId());

        try {
            while (true){
                Thread.sleep(r.nextInt(1000));
                while (sharedQueue.isEmpty()){
                    synchronized (sharedQueue){
                        System.out.println("Queue is empty,consumer " + Thread.currentThread().getId()
                        + "is waiting ,size :" +sharedQueue.size());
                        sharedQueue.wait();
                    }
                }
                synchronized (sharedQueue){
                    System.out.println("consumer consume data:" + sharedQueue.remove(0) + ",size :"+ sharedQueue.size());
                    sharedQueue.notifyAll();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
