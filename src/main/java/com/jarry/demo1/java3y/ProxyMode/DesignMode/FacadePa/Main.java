package com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 11:43
 * 总结：
 * 1、使用了门面模式，使客户端调用变得简单
 * 2、将所有对象的操作封装一层，整出个门面担当ElectricBrake
 */
public class Main {

    //当我们想关闭所有电器时，可以使用电闸来关闭
    public static void main(String[] args) {
        ElectricBrake electricBrake = new ElectricBrake();
        electricBrake.turnOffAll();
    }/**优点：
     a、减少相互依赖、提高灵活性（封装的特性）
     缺点：
     a、不符合开闭原则(扩展开放，修改关闭)

     6大设计准则：
     1、单一职责原则告诉我们实现类要职责单一；
     2、里氏替换原则告诉我们不要破坏继承体系；
     3、依赖倒置原则告诉我们要面向接口编程；
     4、接口隔离原则告诉我们要在设计接口时要精简单一；
     5、迪米特法则告诉我们要降低耦合。
     6、而开闭原则是总纲，他告诉我们要对扩展开放，对修改关闭。

     */
}
