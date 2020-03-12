package com.jarry.demo1.utils.MemUtils;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.MemUtils
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-12 15:15
 */
public interface DistributedLock {

    String acquire();

    boolean release(String indentifier);
}
