package com.jarry.demo1.java3y.ProxyMode.DesignMode.SingletonPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.SingletonPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 12:08
 *
 * 静态内部类巧妙实现 Singleton：
 *
 * 当任何一个线程第一次调用getInstance()时，都会使SingletonHolder被加载和被初始化，
 * 此时静态初始化器将执行Singleton的初始化操作。(被调用时才进行初始化！)
 *
 * 初始化静态数据时，Java提供了的线程安全性保证。(所以不需要任何的同步)
 */
public class StaticInnerClass4LazySin {//静态内部类实现懒汉式(最推荐写法)

    private StaticInnerClass4LazySin(){}

    //使用内部类的方式来实现懒加载
    private static class LazyHolder{
        //创建单例对象
        private static final StaticInnerClass4LazySin INSTANCE = new StaticInnerClass4LazySin();
    }
    //获取对象
    public static final StaticInnerClass4LazySin getInstance(){
        return LazyHolder.INSTANCE;
    }
/**{
 *  1、私有内部类持有了外部类的引用，在被加载时初始化外部类对象的创建（如果已经创建了，直接拿来用就行了）
 *  2、线程安全：Java在加载类时使用的是双亲委派模型，很好的防止了类的重复加载。（【内部也采用了    内存可见性等】故保证了线程安全）
 *  }*/




    /**枚举方式实现
     * 1、简单，直接写就ok
     * 2、防止多次实例化，即使是在面对复杂的序列化或者反射攻击的时候（安全）！*/
    public enum Jarry{
        JA_R_R_Y,
    }
    /*1、枚举类，调用时就初始化好。*/
}