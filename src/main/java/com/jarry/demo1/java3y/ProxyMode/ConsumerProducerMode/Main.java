package com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ConsumerProducerMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 14:41
 */
public class Main {
    static Vector sharedQueue = new Vector();

    public static void main(String[] args) throws InterruptedException {
        //构建内存缓存区

        int size = 3;

        //建立线程池和线程
        ExecutorService service = Executors.newCachedThreadPool();
        Producer prodThread1 = new Producer(sharedQueue, size);
        Producer prodThread2 = new Producer(sharedQueue, size);
        Producer prodThread3 = new Producer(sharedQueue, size);
        Consumer consThread1 = new Consumer(sharedQueue);
        Consumer consThread2 = new Consumer(sharedQueue);
        Consumer consThread3 = new Consumer(sharedQueue);
        service.execute(prodThread1);
        service.execute(prodThread2);
        service.execute(prodThread3);

        service.execute(consThread1);
        service.execute(consThread2);
        service.execute(consThread3);

        Thread.sleep(10 * 1000);
        prodThread1.stop();
        prodThread2.stop();
        prodThread3.stop();

        Thread.sleep(3000);
        synchronized (sharedQueue) {
            consThread1.sharedQueue.notifyAll();//用同一个对象地址去notifyAll可以将所有monitor对象是它的唤醒。
            //比如,快上饭了，哥几个抄家伙

            Thread.sleep(6000);//等这个syn完后，其它人才能获取到
        }

        service.shutdown();
    }
}
