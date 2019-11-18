package com.example.app.threadAsyn;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.threadAsyn
 * @Author: Jarry.Chang
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
