package com.example.app.ThreadAsyn.threadAsyn5;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn5
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:20
 */
public class Customers extends Thread {
    KFC kfc;
    //KFC要传入，保证每一个服务员和用户在同一个KFC对象内
    public Customers(KFC kfc){
        this.kfc = kfc;
    }

    @Override
    public void run(){
        int size = (int) (Math.random()*5);
        while (true){
            kfc.consu(size);
        }
    }
}
