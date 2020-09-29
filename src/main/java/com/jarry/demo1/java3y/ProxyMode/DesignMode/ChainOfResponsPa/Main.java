package com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 16:28
 * <p>
 * 总结：
 * 1、将处理的各个流程抽象为各个类
 * 2、将多个类用 Chain 链起来，暴露一个方法给 Handler 使用
 * 3、do
 * 优点：1、分工明确，解耦，容易维护
 */
public class Main {
    public static void main(String[] args) {
        String data = "我是从http请求中获取到的全量数据";
        FilterChain filterChain = new FilterChain();
        filterChain.processData(data);
    }
}
