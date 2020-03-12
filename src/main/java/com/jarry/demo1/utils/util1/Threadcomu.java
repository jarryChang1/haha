package com.jarry.demo1.utils.util1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @CreateTime: 2019-12-25 10:06
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * t1.start()另开一个线程跑t1的run()
 * t1.run()主线程跑t1的run()方法
 * t1.join（）主线程阻塞，等t1跑完，主线程才能退出
 */


public class Threadcomu {
    private static final int MAX_NUM = 10;
    private static volatile int i = 1;
    private static final Object object = new Object();

    private static Lock lock = new ReentrantLock();//  1.等待可中断        2.公平锁(true)    3.锁绑定多个条件，
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
//                    condition.signal();
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


/**
    public final void await() throws InterruptedException {
        //如果当前线程已经中断，直接抛出异常
        if (Thread.interrupted())
            throw new InterruptedException();
        //(1)将当前线程加入等待队列
        Node node = addConditionWaiter();
        //(2)释放同步状态（也就是释放锁），同时将线程节点从同步队列中移除，并唤醒同步队列中的下一节点
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        //(3)判断当前线程节点是否还在同步队列中，如果不在则阻塞线程
        while (!isOnSyncQueue(node)) {
            LockSupport.park(this);
            if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                break;
        }
        //(4)当线程被唤醒后，重新在同步队列中与其他线程竞争获取同步状态
        if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
            interruptMode = REINTERRUPT;
        if (node.nextWaiter != null) // clean up if cancelled
            unlinkCancelledWaiters();
        if (interruptMode != 0)
            reportInterruptAfterWait(interruptMode);
    }
 */
}
