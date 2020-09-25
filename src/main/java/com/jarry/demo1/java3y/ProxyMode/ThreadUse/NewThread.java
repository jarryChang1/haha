package com.jarry.demo1.java3y.ProxyMode.ThreadUse;

import javax.validation.constraints.AssertTrue;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-04 10:41
 */
public class NewThread {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new myThread();
        Thread thread = new Thread(myThread,"我的线程");
        thread.setDaemon(true);     //将"我的线程"设置为守护线程了
        thread.start();
//        thread.join();
        System.out.println(Thread.currentThread().getName());
        System.out.println(thread.isDaemon());  //多次运行出现，我们的守护线程不执行了。（不打印了）
    }

    static class myThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        }
    }
}
