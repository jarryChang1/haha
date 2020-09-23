package com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-23 17:19
 *
 * 3ynv朋友开了一家奶茶店，一次只能容纳5个人挑选购买，超过就要排队
 */
public class SemaphoreX {
    public static void main(String[] args) {
        //假设有50个顾客同时来到酸奶店门口
        int nums = 50;

        //容纳10个人同时挑选
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < nums; i++) {
            int finalI = i;
            new Thread(()->{
               try {
                   //有”牌“的才能进奶茶店挑选购买
                   semaphore.acquire();

                   System.out.println("顾客" + finalI + "在挑选商品，购买……");
                    //挑选耗时
                   Thread.sleep(1000);

                   System.out.println("顾客" + finalI + "购买完毕了……");
                    //归还一个“狗牌”，后来的顾客就可以购买了
                   semaphore.release();
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
            }).start();

        }

    }
}
