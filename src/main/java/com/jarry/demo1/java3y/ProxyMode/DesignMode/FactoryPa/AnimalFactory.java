package com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 15:57
 */
//号称什么宠物都有
public interface AnimalFactory {
    //可以获取任何种类的宠物
    Animal createAnimal();
}
