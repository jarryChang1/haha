package com.jarry.demo1.constant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.constant
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-10 17:29
 */
public class Main {
    int count = 100000;
    @Autowired
    CyclicBarrier cyclicBarrier;
    @Test
    public void add(){
        SortArrayMap map = new SortArrayMap();

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            double d =Math.random();
            int ran = (int)(d*100);
            map.add(Long.valueOf(i + ran),"127.0.0."+i);
        }
        map.sort();

        long end = System.currentTimeMillis();

        System.out.println("排序耗时："+(end - start));

        System.out.println(map.size);
    }

    private static volatile int  i =0;
    private static volatile int  j =0;
//    public static void main(String[] args) throws InterruptedException {
//        AtomicInteger l = new AtomicInteger(12);//1100
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while( j < 100) {
//                    l.compareAndSet(l.intValue(), 12);
//                    i++;
//                    System.out.println("线程1修改的----------"+l.intValue());
//                    j++;
//                }
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while( j < 100) {
//                    l.compareAndSet(l.intValue(), 12);
//                    i++;
//                    System.out.println("线程2修改的-------"+l.intValue());
//                    j++;
//                }
//            }
//        });
//        thread.start();
//        thread2.start();
//        thread.join();
//        thread2.join();
//        System.out.println(i);
//    }
}
