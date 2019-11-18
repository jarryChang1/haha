package com.example.app.ThreadAsyn.threadAsyn4;

/**
 龟兔赛跑：20米
 要求：
 1.兔子每秒0.5米的速度，每跑2米休息10秒，
 2.乌龟每秒跑0.1米，不休息
 3.其中一个跑到终点后另一个不跑了！
 * @CreateTime: 2019-11-18 11:42
 */
public abstract class Animal extends Thread {

    public double length = 20;//比赛的长度

    public abstract void runing();//抽象方法

    @Override
    public void run(){
        super.run();
        while (length > 0){
            runing();
        }
    }

    //在需要回调数据的地方（两个子类需要）。声明一个接口
    public static interface Callback{
        public void win();
    }

    //创建接口对象
    public Callback callback;
}
