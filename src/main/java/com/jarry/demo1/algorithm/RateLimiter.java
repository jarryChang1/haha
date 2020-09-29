package com.jarry.demo1.algorithm;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.algorithm
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-27 10:00
 */
public class RateLimiter {


//    public double acquire() {
////        com.google.common.util.concurrent.RateLimiter rateLimiter = new com.google.common.util.concurrent.RateLimiter() {
////            @Override
////            public double acquire() {
////                return super.acquire(1);
////            }
////        };
//        return acquire(1);
//    }

//    public double acquire(int permits) {
//        checkPermits(permits);  //检查参数是否合法（是否大于0）
//        long microsToWait;
//        synchronized (mutex) { //应对并发情况需要同步
//            microsToWait = reserveNextTicket(permits, readSafeMicros()); //获得需要等待的时间
//        }
//        ticker.sleepMicrosUninterruptibly(microsToWait); //等待，当未达到限制时，microsToWait为0
//        return 1.0 * microsToWait / TimeUnit.SECONDS.toMicros(1L);
//    }
//
//    private long reserveNextTicket(double requiredPermits, long nowMicros) {
//        resync(nowMicros); //补充令牌
//        long microsToNextFreeTicket = nextFreeTicketMicros - nowMicros;
//        double storedPermitsToSpend = Math.min(requiredPermits, this.storedPermits); //获取这次请求消耗的令牌数目
//        double freshPermits = requiredPermits - storedPermitsToSpend;
//
//        long waitMicros = storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend)
//                + (long) (freshPermits * stableIntervalMicros);
//
//        this.nextFreeTicketMicros = nextFreeTicketMicros + waitMicros;
//        this.storedPermits -= storedPermitsToSpend; // 减去消耗的令牌
//        return microsToNextFreeTicket;
//    }
//
//    private void resync(long nowMicros) {
//        // if nextFreeTicket is in the past, resync to now
//        if (nowMicros > nextFreeTicketMicros) {
//            storedPermits = Math.min(maxPermits,
//                    storedPermits + (nowMicros - nextFreeTicketMicros) / stableIntervalMicros);
//            nextFreeTicketMicros = nowMicros;
//        }
//    }
}
