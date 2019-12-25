package com.jarry.demo1.utils.util1;

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
                System.out.println("thread1 local:"+threadLocal.get());
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("b");
                System.out.println("thread2 local:"+threadLocal.get());
            }
        });
        thread.start();
        thread1.start();
        threadLocal.set("main");
        System.out.println("main local :"+threadLocal.get());
    }
}
