package com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 16:23
 */
public interface Filter {
    //过滤
    void doFilter(String data);
}
