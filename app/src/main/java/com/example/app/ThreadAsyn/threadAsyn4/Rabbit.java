package com.example.app.ThreadAsyn.threadAsyn4;

import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn4
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 11:46
 */
@Slf4j
public class Rabbit extends Animal {
    public Rabbit() {
        setName("兔子");// Thread的方法，给线程赋值名字
    }
    @Override
    public void runing() {
        double dis = 0.5;
        length -= dis;//跑完距离要减少
        if(length <= 0){
            length = 0;
            System.out.println("兔子获得胜利");
            //给回调对象赋值，让乌龟不用跑了
            if(callback !=null){
                callback.win();
            }
        }
        log.info("兔子跑了{}米，距离终点还有{}米",dis,length);
        if(length % 2 == 0){
            try {
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
