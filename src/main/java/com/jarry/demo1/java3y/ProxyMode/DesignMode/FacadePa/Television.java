package com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 11:33
 */
public class Television {

    //关闭电视
    public void turnOffTV(){
        System.out.println("关闭电视");
    }
    public void doSomething(){
        System.out.println("切换电视节目..降低声音..调高声音...调整色彩等");
    }

    public class Computer{

        public void turnOffCom(){
            System.out.println("关闭电脑");
        }

        public void doSomething(){
            System.out.println("使用电脑,学习..看剧..search..");
        }
    }
}
