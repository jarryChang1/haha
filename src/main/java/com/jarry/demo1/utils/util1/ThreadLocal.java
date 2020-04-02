package com.jarry.demo1.utils.util1;

import com.jarry.demo1.Demo1Application;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-25 15:05
 */
public class ThreadLocal {
    private static java.lang.ThreadLocal<String> threadLocal = new java.lang.ThreadLocal<>();

    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("a");
                Demo1Application.str = "110111";
                System.out.println("thread1 local:"+threadLocal.get()+Thread.currentThread().getName());
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("b");
                Demo1Application.str = "110110";
                System.out.println("thread2 local:"+threadLocal.get()+Thread.currentThread().getName());
            }
        });
        thread.start();
        thread1.start();
        threadLocal.set("main");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Demo1Application.str);
        System.out.println("main local :"+threadLocal.get());
    }
}
