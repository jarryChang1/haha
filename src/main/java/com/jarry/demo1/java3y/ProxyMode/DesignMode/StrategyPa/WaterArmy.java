package com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 10:47
 */
/*请水军（ConcreteStrategy）*/
public class WaterArmy implements IncreaseFansStrategy{

    @Override
    public void action() {
        System.out.println("3y牛逼，我要给你点赞、转发、加鸡腿!");
    }

}
