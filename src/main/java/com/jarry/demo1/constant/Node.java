package com.jarry.demo1.constant;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.constant
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-10 17:09
 */
@Data
public class Node extends Thread {
    public Long key;

    public String value;

    Node(Long key, String value) {
        this.key = key;

        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key = " + key +
                ", value" + value + '\'' +
                '}';
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("循环一次");
            // ... 单次循环代码
        }
        System.out.println("done ");
    }

    //    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Node(1L,"sss");
//        thread.start();
//        Thread.sleep(1000);
//        thread.interrupt();
//    }
//public static void main(String[] args) throws InterruptedException {
//    Thread t = new Thread() {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println(isInterrupted());
//            }
//        }
//    };
//    t.start();
//    try {
//        Thread.sleep(100);
//    } catch (InterruptedException e) {
//    }
//    t.interrupt();
//}
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
        }, "child thread -1");

        t1.start();
        Thread.sleep(1000);
        System.out.println(t1.isAlive());
        t1.interrupt();//线程相应中断后，捕获了异常，正常退出了
        System.out.println(t1.isAlive());
        Thread.sleep(1000000);
    }
}
