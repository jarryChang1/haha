package com.jarry.demo1.java3y.ProxyMode.DesignMode.TemplatePa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 12:16
 * <p>
 * 模板方法模式也很简单呀，
 * 一个抽象类有基本方法(等着被子类实现的方法)，有模板方法(对外暴露、调用基本方法、定义了算法的框架)，那就完事了。
 */
public class TemplatePa {
    //模板方法:
    public void acquire(int arg) {
        if (!tryAcquire(arg))
            return;
        else System.out.println("获取成功");
    }

    /**
     * 基本方法（等着被重写实现）
     */
    private boolean tryAcquire(int arg) {
        //不同的操作（比如公平锁的检验队列有没有前驱，没有前驱:就公平了）
        // 再比如读写检验读写状态
        return false;
    }
}
