package com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.StrategyPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 10:52
 */
/**
 * 3y（Context）
 */
public class JavaContext {

    private IncreaseFansStrategy strategy;

    public JavaContext(IncreaseFansStrategy strategy){
        this.strategy = strategy;
    }

    //3y要发文章了（买水军了、送书了、写知乎引流了……）
    // 具体执行哪个，看3y选哪个策略
    public void exec(){
        strategy.action();
    }

}
