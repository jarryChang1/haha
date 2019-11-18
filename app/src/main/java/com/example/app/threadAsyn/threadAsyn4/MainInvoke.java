package com.example.app.ThreadAsyn.threadAsyn4;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn4
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 12:06
 */
public class MainInvoke {
    /**
     * 龟兔赛跑:20m
     */
    public static void main(String[] args){
        Rabbit rabbit =new Rabbit();
        Tortoise tortoise = new Tortoise();

        LetOneStop letOneStop = new LetOneStop(tortoise);
        rabbit.callback = letOneStop;

        LetOneStop letOneStop1 = new LetOneStop(rabbit);
        tortoise.callback = letOneStop1;

        tortoise.start();
        rabbit.start();
    }
}
