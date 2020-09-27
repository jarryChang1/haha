package com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter;

import lombok.ToString;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 17:48
 */
@ToString
public class Person {
    //成员有一个监听器对象
    private PersonListener personListener;

    //能吃
    public void eat(){
        //当这个人调用了吃eat，应该触发监听器的方法doEat，（把这个事件对象给传递进去）
        personListener.doEat(new Event(this));
    }

    //能睡
    public void sleep(){
        personListener.doSleep(new Event(this));
    }

    //注册监听器，给这个人对象加一个监听器
    public void registerListener(PersonListener personListener){
        this.personListener = personListener;
    }

    public void doExercise() {
        System.out.println( this +"我正在努力锻炼");
    }
}
