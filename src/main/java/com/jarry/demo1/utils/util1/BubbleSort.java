package com.jarry.demo1.utils.util1;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-24 16:40
 */
@Slf4j
public class BubbleSort  {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.info("线程池执行中，当前线程是:{}",Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        service.execute(t);
        t.start();
    }
//    public static void main(String[] args) {
//        int[] ints= {1,2,4,9,5,6,7,6};
//        for (int i = 0; i < ints.length; i++) {
//
//            for (int j = 0; j < ints.length-i-1; j++) {
//                if(ints[j]>ints[j+1]) {
//                    int temp = ints[j];
//                    ints[j]=ints[j+1];
//                    ints[j+1]=temp;
//                }
//            }
//
//        }
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(ints[i]);
//        }
//    }

}
