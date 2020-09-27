package com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 17:53
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        person.registerListener(new PersonListener() {
            @Override
            public void doEat(Event event) {
                Person person1 = event.getResource();

                System.out.println(person1 + "正在吃饭呢!");

                //打电话让3y少吃点
   /*             call();*/
                //person这个人处理锻炼，减肥
                person1.doExercise();
            }

            @Override
            public void doSleep(Event event) {
                Person person1 = event.getResource();

                System.out.println(person1 + "正在睡觉呢!");
            }

            @Override
            public void call() {
                System.out.println("我要打电话让3y少吃点，罚他一个月不吃正餐");
            }
        });

        //这时调用eat方法， 将事件 传递给监听器；监听器做出相应的操作
        person.eat();
    }
}
