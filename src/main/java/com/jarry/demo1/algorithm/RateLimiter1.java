package com.jarry.demo1.algorithm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.algorithm
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-27 14:07
 */
public class RateLimiter1 {

    private volatile int token;

    private final int originToken;

    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private final Object lock = new Object();

    static {
        try {
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(clazz);
            valueOffset = unsafe.objectFieldOffset(RateLimiter1.class.getDeclaredField("token"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public RateLimiter1(int token) {
        this.originToken = token;
        this.token = token;
    }

    /**
     * 获取一个令牌
     */
    public boolean acquire() {
        int current = token;
        if (current <= 0) {
            current = originToken;
        }
        long expect = 1000;//max wait 1s
        long future = System.currentTimeMillis() + expect;
        while (current > 0) {
            if (compareAndSet(current, current - 1)) {//current
                return true;
            }
            current = token;
            if (current <= 0 && expect > 0) {//expect超时时间
                synchronized (lock) {
                    try {
                        lock.wait(expect);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                current = token;
                if (current <= 0) {
                    current = originToken;
                }
                expect = future - System.currentTimeMillis();
            }
        }
        return false;
    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * 补充令牌
     */
    public void supplement(final ExecutorService executorService) {
        this.token = originToken;
        executorService.execute(() -> {
            synchronized (lock) {
                lock.notifyAll();
            }
        });
    }
}
