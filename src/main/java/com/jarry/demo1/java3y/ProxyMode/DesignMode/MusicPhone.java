package com.jarry.demo1.java3y.ProxyMode.DesignMode;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 10:20
 * <p>
 * 有了装饰器后，我们的扩展都可以以装饰器为基础进行扩展，
 * 继承装饰器来扩展就OK了!
 */
//继承着装饰器来扩展
public class MusicPhone extends DecoratorPattern {

    public MusicPhone(Phone phone) {
        super(phone);
    }

    //定义想要扩展的功能
    public void listenMusic() {
        System.out.println("安静的听 心跳声 唱歌，人潮在汹涌，你却还孤单！");
    }


    //重写打电话的方法(继承了装饰器来扩展call，同理也可以在打完电话后操作一件事：输出当前时间)
    @Override
    public void call() {
        //在打电话之前听音乐
        listenMusic();
        super.call();
        currentTime();
    }

    //定义想要扩展的功能
    public void currentTime() {
        System.out.println("当前时间为：" + System.currentTimeMillis());
    }
}
