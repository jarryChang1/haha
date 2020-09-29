package com.jarry.demo1.java3y.ProxyMode.DesignMode;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 10:40
 */
public class HFPhone extends DecoratorPattern {
    //将这个phone传递给   装饰器Pattern
    public HFPhone(Phone phone) {
        super(phone);
    }

    public void HandsFree() {
        System.out.println("开启免提，使用扬声器进行打电话");
    }

    @Override
    public void call() {
        super.call();
        HandsFree();
    }
}
