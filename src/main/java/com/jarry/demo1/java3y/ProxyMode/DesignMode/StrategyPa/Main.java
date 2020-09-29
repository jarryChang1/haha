package com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 10:55
 * 总结：
 * 1、Context是用来包装Strategy策略的
 * 2、传入的是实现具体（干活）接口的对象实体
 * 3、算法可自由切换,扩展性良好
 */
public class Main {
    public static void main(String[] args) {

        //使用水军策略
        JavaContext javaContext = new JavaContext(new WaterArmy());
        javaContext.exec();

        //使用原创策略
        JavaContext javaContext1 = new JavaContext(new ConcentrateBeOriginal());
        javaContext1.exec();

    }
    /**
     * 策略类数量增多
     * 所有策略类都需要对外暴露
     */
}
