package com.jarry.demo1.java3y.ProxyMode.DesignMode;
//和IO非常相像
/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 09:56
 *
 * 总结：构造器中传一个phone的实现类，然后完成接口中的方法。
 * 然后别的类去继承这个【装饰器】，然后又可以重写call方法来实现灵活的装饰。（在重写的call方法中调用实例phone的call方法）
 */
//装饰器，实现接口
public class DecoratorPattern implements Phone{

    //以组合的方式来获取默认实现类
    private Phone phone;
    public DecoratorPattern(Phone phone){
        this.phone = phone;
    }

    @Override
    public void call() {
        phone.call();
    }
}
