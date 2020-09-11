package com.jarry.demo1.java3y.ProxyMode.LeakBuket;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.LeakBuket
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-04 09:23
 */
public class LeakBucket {
    public static void main(String[] args) {
        ExecutorService exex = Executors.newCachedThreadPool();

        final RateLimiter rateLimiter = RateLimiter.create(3.0);

        for (int i = 0; i < 100; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        rateLimiter.acquire();
                        System.out.println("Accessing:" + no + ",time:"
                        + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            exex.execute(runnable);
        }
        exex.shutdown();
    }
    long timeStamp = System.currentTimeMillis();//上次时间戳
    int capacity;//桶的容量
    int rate;//速率
    long water;//水量
    boolean grant(){//补水
        long now = System.currentTimeMillis();
        water = Math.max(0L,water - (now - timeStamp)*rate);//water - （时间差）*速率 == 水量
        timeStamp = now;//时间戳刷新
        if (water < capacity){
            water++;
            return true;
        }else {
            return false;
        }
    }
}
