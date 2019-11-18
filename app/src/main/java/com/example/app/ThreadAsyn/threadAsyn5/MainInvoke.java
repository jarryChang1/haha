package com.example.app.ThreadAsyn.threadAsyn5;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn5
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:24
 */
public class MainInvoke {
    public static void main(String[] args){
        KFC kfc = new KFC();

        Customers c1 = new Customers(kfc);
        Customers c2 = new Customers(kfc);
        Customers c3 = new Customers(kfc);
        Customers c4 = new Customers(kfc);

        Waiter w1 = new Waiter(kfc);
        Waiter w2 = new Waiter(kfc);
        Waiter w3 = new Waiter(kfc);

        w1.start();w2.start();
        w3.start();c1.start();
        c2.start();c3.start();
        c4.start();
    }
}
