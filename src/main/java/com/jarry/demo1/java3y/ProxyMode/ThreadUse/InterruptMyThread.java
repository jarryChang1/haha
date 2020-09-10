package com.jarry.demo1.java3y.ProxyMode.ThreadUse;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ThreadUse
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-04 11:51
 */
public class InterruptMyThread {
    /*
    调用interrupt()并不是真正终止掉当前线程，仅仅是设置了一个中断标志位
    中断标志位交给我们自行判断是否要终止线程。
    调用interrupt()并不是要真正终止掉当前线程，仅仅是设置了一个中断标志。
    这个中断标志可以给我们用来判断什么时候该干什么活！什么时候中断由我们自己来决定，
    这样就可以安全地终止线程了！
    比如用Stop真正意义干掉线程*/
    public static void main(String[] args) {
        InterruptMyThread interruptMyThread = new InterruptMyThread();
        //创建线程并启动
        Thread t = new Thread(interruptMyThread.runnable);
        System.out.println("This is main");
        t.start();

        try {
            Thread.sleep(3100);
        }catch (InterruptedException e){
            System.out.println("In main");
            e.printStackTrace();
        }
        t.interrupt();
    }

    Runnable runnable = () ->{
        int i = 0;
        try {
            while (i < 1000){//本来要输出1000次的
                Thread.sleep(500);
                System.out.println(i++);
            }
        }catch (InterruptedException e){//结果调用了interrupt;抛出了中断异常。
            //其实他还活着、还未被中断；in runnable

            System.out.println(Thread.currentThread().isAlive());

            System.out.println(Thread.currentThread().isInterrupted());

            System.out.println("In runnable");

            e.printStackTrace();
        }
    };
}
