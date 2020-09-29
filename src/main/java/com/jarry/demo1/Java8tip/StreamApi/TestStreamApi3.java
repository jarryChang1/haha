package com.jarry.demo1.Java8tip.StreamApi;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.StreamApi
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-25 16:00
 */
public class TestStreamApi3 {
    /**
     * 查找与匹配
     * allMatch --检查是否匹配所有元素
     * anyMatch --检查是否至少匹配一个元素
     * noneMatch --检查是否没有匹配任何元素
     * <p>
     * //Optional<T>
     * findFirst --返回第一个元素
     * FindAny --返回当前流中的任意元素
     * count  --流中元素总个数
     * max  --最大值
     * min  --最小值
     */


    Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6);

    /**
     * 求和，也可以写成Lambda语法：
     * Integer sum = s.reduce((a, b) -> a + b).get();
     */

    @Test
    public void test1() {

        //其返回的结果是一个Optional对象
        Integer sum1 = s.reduce((a, b) -> a + b).get();

        Integer sum = s.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).get();
        System.out.println(sum1);

        /**
         * 求最大值，也可以写成Lambda语法：
         * Integer max = s.reduce((a, b) -> a >= b ? a : b).get();
         */
        Integer max = s.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer >= integer2 ? integer : integer2;
            }
        }).get();
    }


}
