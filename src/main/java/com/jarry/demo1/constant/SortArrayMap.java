package com.jarry.demo1.constant;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-10 16:57
 */
public class SortArrayMap {
    /**
     * 核心组件
     */
    private Node[] buckets;

    private static final int DEFAULT_SIZE = 10;

    int size = 0;

    public SortArrayMap(){
        buckets = new Node[DEFAULT_SIZE];
    }

    /**
     * Fnv1_32_hash算法
     * @param key
     * @return
     */
    public  long  hash(String  key)  {
        final  int  p  =  16777619;
        int  hash  =  (int)2166136261L;
        for(int  i  =  0;  i  <  key.length();  i++)  {
            hash  =  (hash  ^  key.charAt(i))  *  p;
        }
        hash  +=  hash  <<  13;
        hash  ^=  hash  >>  7;
        hash  +=  hash  <<  3;
        hash  ^=  hash  >>  17;
        hash  +=  hash  <<  5;
        // 如果算出来的值为负数则取其绝对值
        if  (hash  <  0)  hash  =  Math.abs(hash);
        return  hash;
    }
    /**
     * 顺时针取出数据，没查到就去第一个
     * @param key
     * @return
     */
    public String firstNodeValue(long key){
        if (size ==0){
            return null;
        }
        for (Node bucket : buckets){
            if (bucket == null)  continue;
            if (bucket.key >= key)  return bucket.value;
        }
        return buckets[0].value;
    }


    public void sort(){
//        Arrays.sort(buckets,0,size,((o1,o2) -> o1.key.compareTo(o2.key)));
        Arrays.sort(buckets,0,size,(Comparator.comparing(o -> o.key)));
    }
    /**
     * 写入数据
     * @param key
     * @param value
     */
    public void add(Long key,String value){
        checkSize(size +1);
        Node node = new Node(key,value);

        buckets[size++] = node;

    }

    /**
     * 校验是是否扩容
     * @param size
     */
    public void checkSize(int size){
        if (size >= buckets.length){
            int oldlen = buckets.length;

            int newlen = oldlen + (oldlen >> 1);
            buckets = Arrays.copyOf(buckets,newlen);
        }
    }
}
