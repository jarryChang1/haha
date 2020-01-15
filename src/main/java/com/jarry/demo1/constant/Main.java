package com.jarry.demo1.constant;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.constant
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-10 17:29
 */
public class Main {
    int count = 100000;
    @Test
    public void add(){
        SortArrayMap map = new SortArrayMap();

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            double d =Math.random();
            int ran = (int)(d*100);
            map.add(Long.valueOf(i + ran),"127.0.0."+i);
        }
        map.sort();

        long end = System.currentTimeMillis();

        System.out.println("排序耗时："+(end - start));

        System.out.println(map.size);
    }
}
