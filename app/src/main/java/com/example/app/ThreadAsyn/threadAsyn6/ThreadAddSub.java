package com.example.app.ThreadAsyn.threadAsyn6;

import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn6
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 14:45
 */
@Slf4j
public class ThreadAddSub extends  Thread {
    //判断要进行的操作；
    boolean operate = true;

    //要操作的数
    static int sum = 0;

    //把操作运算通过构造方法传进来
    public ThreadAddSub(boolean operate){
        super();
        this.operate = operate;
    }
    @Override
    public  void  run(){
        super.run();
        while (true){
            if(operate){
                sum += 5;
                log.info("加后，sum = "+sum);
            }else {
                sum -= 4;
                log.info("减后，sum = "+sum);
            }
            try {
                sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
