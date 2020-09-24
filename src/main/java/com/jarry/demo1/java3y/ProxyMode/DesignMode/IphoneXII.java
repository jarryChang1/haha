package com.jarry.demo1.java3y.ProxyMode.DesignMode;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-24 10:18
 */
public class IphoneXII implements Phone{
    @Override
    public void call() {
        System.out.println("打电话给周围的人来加入我的项目");
    }
}
