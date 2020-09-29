package com.jarry.demo1.Java8tip.StreamApi;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @CreateTime: 2019-09-25 11:45
 * <p>
 * 一、Stream的三个操作步骤：
 * 1、创建Stream
 * <p>
 * 2、中间操作
 * 3、终止操作
 */
public class TestStreamApi1 {
    //创建Stream的4中方式
    @Test
    public void test1() {

        //1、Collection集合提供的stream()或者parallelStream()方法
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2、通过Arrays 中的静态方法stream()获取数组流
        UserBean[] userBeans = new UserBean[12];
        Stream<UserBean> stream1 = Arrays.stream(userBeans);

        //3、通过Stream 类中的静态方法of()生成
        Stream<String> stream2 = Stream.of("a", "b", "c");

        //4、创建无线流
        //①迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(100).forEach(System.out::println);
        //②生成
        Stream.generate(() -> Math.random()).limit(100).forEach(System.out::println);

    }

}
