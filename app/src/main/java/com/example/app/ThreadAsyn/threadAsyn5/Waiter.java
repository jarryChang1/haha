package com.example.app.ThreadAsyn.threadAsyn5;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn5
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:23
 */
public class Waiter extends Thread {
    KFC kfc;
    public Waiter(KFC kfc){
        this.kfc = kfc;
    }

    @Override
    public  void  run(){
        int size = (int)(Math.random()*5)+5;
        while (true) kfc.prod(size);
    }
}
