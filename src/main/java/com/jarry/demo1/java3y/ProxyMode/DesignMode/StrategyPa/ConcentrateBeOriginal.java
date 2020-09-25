package com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 10:50
 */
/**
 * 专注原创（ConcreteStrategy）
 */
public class ConcentrateBeOriginal implements IncreaseFansStrategy{

    @Override
    public void action() {
        System.out.println("3y认真写原创，最新一篇文章:《策略模式,就这?》");
    }

}
