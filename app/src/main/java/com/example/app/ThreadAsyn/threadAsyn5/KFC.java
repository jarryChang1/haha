package com.example.app.ThreadAsyn.threadAsyn5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn5
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:07
 */
@Slf4j
public class KFC {
    String[] name = {"薯条","烧板","鸡翅","可乐"};
    //生产的最大值，达到后可以休息
    static final int Max = 20;

    List<Food> foods = new ArrayList<>();

    public void prod(int index){
        synchronized (this){
            //如果食物数量大于20
            while (foods.size() > Max){
                System.out.println("食材够了");
                this.notifyAll();
                try {
                    String name = Thread.currentThread().getName();
                    this.wait();
                    System.out.println("生产者:"+name);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("开始生产食物");
            for (int i = 0; i < index; i++) {
                Food food = new Food(name[(int)(Math.random()*4)]);
                foods.add(food);
                log.info("生产了{},{}",food.getName(),foods.size());
            }
        }
    }

    public void consu(int index){
        synchronized (this){
            while (foods.size() < index){
                System.out.println("食材不够了");
                this.notifyAll();
                try{
                    String name = Thread.currentThread().getName();
                    this.wait();
                    System.out.println("消费者"+name);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("开始消费");
            for (int i = 0; i < index; i++) {
                Food food = foods.remove(foods.size() -1);
                log.info("消费了一个{},{}",food.getName(),foods.size());
            }
        }
    }
}
