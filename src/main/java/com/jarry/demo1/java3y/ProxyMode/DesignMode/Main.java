package com.jarry.demo1.java3y.ProxyMode.DesignMode;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 10:28
 * <p>
 * 解析：
 * 第一步：我们有一个Phone接口，该接口定义了Phone的功能
 * <p>
 * 第二步：我们有一个最简单的实现类iPhoneXII
 * <p>
 * 第三步：写一个装饰器抽象类PhoneDecorate，以组合(构造函数传递)的方式接收我们最简单的实现类iPhoneXII。
 * 其实装饰器抽象类的作用就是代理(核心的功能还是由最简单的实现类iPhoneXII来做，只不过在扩展的时候可以添加一些没有的功能而已)。
 * <p>
 * 第四步：想要扩展什么功能，就继承PhoneDecorate装饰器抽象类，将想要增强的对象(最简单的实现类iPhoneXII或者已经被增强过的对象)传进去，完成我们的扩展！
 * -------------------------------------------------------------------------------------------------------------------------------
 * * 总结：构造器中传一个phone的实现类，然后完成接口中的方法。
 * * 然后别的类去继承这个【装饰器】，然后又可以重写call方法来实现灵活的装饰。（在重写的call方法中调用实例phone的call方法）
 */
public class Main {
    public static void main(String[] args) {
        Phone phone = new IphoneXII();/**最核心的原始类*/

        //如果不想装饰了，就不装了
        phone = new MusicPhone(phone);/**MusicPhone继承着装饰器，将原始核心类传递增强*/
        /**返回的这个phone已被增强过一次了*/
        phone.call();
        System.out.println("------------------------------------------------");
        /**还可以继续增强，再通过构造函数包装一次，就可以增强其它的功能了*/
        phone = new HFPhone(phone);

        phone.call();
    }
}
