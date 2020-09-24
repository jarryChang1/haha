package com.jarry.demo1.java3y.ProxyMode.DesignMode.SingletonPa;


/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.SingletonPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 11:48
 */
public class Singleton {

    //饿汉式
    private Singleton(){}

    private static final Singleton singleton = new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }
    //懒汉式-----------------------------------------------------------
    /**简单懒汉式(在方法加锁)*/
    static class Jarry{
        //1、将构造函数私有化，不可以通过new的方式来创建
        private Jarry(){}
        //2.1先不创建对象，等用的时候再创建
        private static Jarry jarry = null;
        //2.2调用到这个方法了，证明是被用到的了
        public static synchronized Jarry getJarry(){
            //3.如果这个对象引用为null,则创建并返回
            if (jarry == null){
                jarry = new Jarry();
            }
            return jarry;
        }
    }
    /**缩小锁范围*/
    static class Jarry1{
        //1、将构造函数私有化，不可以通过new的方式来创建
        private Jarry1(){}

        private static Jarry1 jarry = null;

        public static  Jarry1 getJarry1(){

            if (jarry == null){
                //将锁的范围缩小，提高性能
                synchronized(Jarry1.class){
                    jarry = new Jarry1();
                }
            }
            return jarry;
        }
    }/**这种方式在判断时就应该加锁了，
     （进入同步代码块时再判断一下对象是否存在就稳了吧！） ->其实还不稳！这里会有【代码重排】的问题：
     原因：因为new jarry1我们知道并不是原子操作，对象创建时的三级缓存 ，有可能其它程序还认为它为null*/

    /**DCL双重检测加锁(进阶懒汉式)*/
    static class Jarry2{
        //1、将构造函数私有化，不可以通过new的方式来创建
        private Jarry2(){}

        private static  volatile Jarry2 jarry = null;/**使用volatile当内存屏障*/

        public static  Jarry2 getJarry2(){

            if (jarry == null){
                //将锁的范围缩小，提高性能
                synchronized(Jarry2.class){
                    //再判断一次是否为Null
                     if (jarry == null){
                     jarry = new Jarry2();
                    }
                }
            }
            return jarry;
        }
    }

}
