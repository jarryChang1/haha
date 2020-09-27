package com.jarry.demo1.java3y.ProxyMode.DesignMode.BuilderPa;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.BuilderPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 10:17
 */
public class Main {
    public static void main(String[] args) {
        MessageTask.Builder builder = new MessageTask.Builder();
        MessageTask task = builder.setTaskId("1build")
                .setTaskName("一起来玩呀")
                .setContent("关注 Java3y 吧 >>")
                .setMessageId(String.valueOf(ThreadLocalRandom.current().nextLong()))
                .build();
        System.out.println(task);
    }
}
