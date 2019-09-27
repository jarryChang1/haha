package com.jarry.demo1.Java8tip;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-24 14:27
 */

//函数式接口，此注解可以检查接口是不是函数式接口，不允许多个抽象方法
@FunctionalInterface
interface MyLambdaInterface{
    void doSomething(String s);
    //接口可以支持静态方法，有方法体
    static void lll(String s1){
        System.out.println(s1);
    }
}
