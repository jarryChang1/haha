package com.example.app.ThreadAsyn.threadAsyn4;

import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn4
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 11:55
 */
@Slf4j
public class Tortoise extends Animal {
    public Tortoise() {
        setName("乌龟");// Thread的方法，给线程赋值名字
    }
    @Override
    public void runing() {
    //跑的距离
        double dis = 0.1;
        length -= dis;
        if (length <= 0){
            length = 0;
            System.out.println("乌龟获得了胜利");
            //让兔子别跑了
            if (callback != null) callback.win();

        }
        log.info("乌龟跑了{}米，距离终点还有{}米",dis,(int)length);
        try{
            sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
