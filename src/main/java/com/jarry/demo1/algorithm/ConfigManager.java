package com.jarry.demo1.algorithm;

import org.apache.lucene.util.NamedThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.AccessControlException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.algorithm
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-27 14:35
 */
@Component
public class ConfigManager {

    private final ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(2);

    private final ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
            new NamedThreadFactory("supplement"));

    private Map<String, RateLimiter1> rateLimiter1Map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        scheduledCheck.scheduleAtFixedRate(new SupplementRateLimiter(), 1, 1, TimeUnit.SECONDS);
    }

    @PostConstruct
    public void destroy() {
        scheduledCheck.shutdown();
    }

    public void acquire(String key, int tokenCount) {
        RateLimiter1 rateLimiter1 = rateLimiter1Map.get(key);
        //双检锁确保安全创建
        if (rateLimiter1 == null) {
            synchronized (this) {
                //init RateLimiter1
                rateLimiter1 = rateLimiter1Map.computeIfAbsent(key, k -> new RateLimiter1(tokenCount));
            }
        }
        //尝试获取令牌
        if (!rateLimiter1.acquire()) {
            //如果获取失败，根据实际情况做处理.这里直接抛异常了
            throw new RuntimeException();
        }
    }


    public class SupplementRateLimiter implements Runnable {
        @Override
        public void run() {
            rateLimiter1Map.values().forEach(RateLimiter1 -> RateLimiter1.supplement(executorService));
        }
    }
}
