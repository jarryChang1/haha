package com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse.CCS
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-23 17:18
 */
public class CyclicBarrierX {
    public static void main(String[] args) {

        final CyclicBarrier CyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                String name = Thread.currentThread().getName();
                if (name.equals("Thread-0")) {
                    name = "3y";
                } else {
                    name = "女朋友";
                }
                System.out.println(name + "到了体育西");
                try {

                    // 两个人都要到体育西才能发朋友圈
                    CyclicBarrier.await();
                    // 他俩到达了体育西，看见了对方发了一条朋友圈：
                    System.out.println("跟" + name + "去夜上海吃东西~");

                    // 回家
                    CyclicBarrier.await();
                    System.out.println(name + "洗澡");

                    // 洗澡完之后一起聊天
                    CyclicBarrier.await();

                    System.out.println("一起聊天");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
