package com.example.app.ThreadAsyn.threadAsyn6;

/**
 同步---异步传统方法的对比
 */
public class Hello {

    public static String threadAsynchSayHello() {
        //还是休眠3秒
        Runnable runnable = ()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println(Thread.currentThread().getName()+"  开启新线程");
        new Thread(runnable).start();//开启线程去跑，我就不管了直接返回了
        return "hello world,这是开启线程的异步方法";
    }

    public static String threadsynchSayHello() {
        //还是休眠3秒
//        Runnable runnable = ()-> {
            try {
                System.out.println("线程名: " +Thread.currentThread().getName());
                System.out.println("开始同步休眠3秒");
                Thread.sleep(3000);
                System.out.println("同步休眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        };
        System.out.println(Thread.currentThread().getName()+"  开启新线程");
//        new Thread(runnable).start();
        return "hello world,这是开启线程的同步方法";
    }
}
