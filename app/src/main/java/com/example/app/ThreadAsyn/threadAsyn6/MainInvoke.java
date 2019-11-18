package com.example.app.ThreadAsyn.threadAsyn6;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn6
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:50
 */
public class MainInvoke {
    public static void main(String[] args) {
        //创建一个存放ThreadAddSub对象的数组
        ThreadAddSub[] threadAddSubs = new ThreadAddSub[4];

        for (int i = 0; i < threadAddSubs.length; i++) {
            //把实例化ThreadAddSub对象赋值到数组内
            threadAddSubs[i] = new ThreadAddSub(i%2 == 0 ? true:false);
            threadAddSubs[i].start();
        }
    }
}
