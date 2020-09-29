package com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FactoryPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 16:05
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
