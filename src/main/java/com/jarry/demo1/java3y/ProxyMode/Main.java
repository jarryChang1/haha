package com.jarry.demo1.java3y.ProxyMode;

import java.lang.reflect.Proxy;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-02 17:30
 */
public class Main {

//    //静态代理（需要自己编写代理类）
//    public static void main(String[] args) {
//
//        //想要发达的Java3y
//        Java3y java3y = new Java3y();
//
//        //受委托的programmer大V
//        Programmer programmerBigV = new ProgrammerBigV(java3y);
//
//        //受委托的Programmer大V让Java3y来写文章、发文章;大V自己来点赞
//        programmerBigV.coding();
//    }

    /**
     * 动态代理（动态生成代理类）
     */
    public static void main(String[] args) {
        //Java3y请水军
        Java3y java3y = new Java3y();

        Programmer programmerWaterArmy = (Programmer) Proxy.newProxyInstance(java3y.getClass().getClassLoader(),
                java3y.getClass().getInterfaces(),
                (proxy, method, args1) -> {

                    // 如果是调用coding方法，那么水军就要来点赞了
                    if (method.getName().equals("coding")) {
                        method.invoke(java3y, args1);
                        System.out.println("我是水军，我来点赞了!");
                    } else {
                        return method.invoke(java3y, args1);
                    }
                    return null;
                });
        programmerWaterArmy.coding();
    }

}
