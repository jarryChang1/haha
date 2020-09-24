package com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 16:10
 *
 * 总结：
 *   1:客户端不需要再负责对象的创建,明确了各个类的职责
 *
 *   2:如果有新的对象增加,只需要增加一个具体的类和具体的工厂类即可
 *
 *   3:不会影响已有的代码,后期维护容易,增强系统的扩展性
 *
 *   缺点：1:需要额外的编写代码,增加了工作量
 */
public class Main {
    public static void main(String[] args) {
        AnimalFactory f = new DogFactory();
        AnimalFactory ff = new CatFactory();
        Animal a = f.createAnimal();
        Animal animal = ff.createAnimal();
        a.eat();
        animal.eat();
        System.out.println("使用狗狗工厂来创建动物，然后eat");
        System.out.println("使用猫猫工厂来创建动物，然后eat");
    }
}
