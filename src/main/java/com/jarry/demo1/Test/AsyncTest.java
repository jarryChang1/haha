package com.jarry.demo1.Test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-05-21 14:41
 */
@Slf4j
public class AsyncTest {


    @Async
    public Future<Integer> excute01AsyncWithFuture(){
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    public Integer execute01() {
        log.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        log.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i+" "+j+" "+k);
    }
}
