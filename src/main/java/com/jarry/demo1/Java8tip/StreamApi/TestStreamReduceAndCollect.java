package com.jarry.demo1.Java8tip.StreamApi;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.StreamApi
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-25 17:47
 */
public class TestStreamReduceAndCollect {

    List<UserBean> users = Arrays.asList(
            new UserBean("李红", 22, 22, 34),
            new UserBean("赵四", 36, 22, 46),
            new UserBean("王五", 22, 22, 33),
            new UserBean("张三", 58, 22, 47)
    );
    /**
     *   收集
     *   collect --将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     */
    /**
     * 分区
     * ture和false分区
     */
    @Test
    public void test4() {
        Map<Boolean, List<UserBean>> map = users.stream()
                .collect(Collectors.partitioningBy(x -> x.getAge() > 35));
        System.out.println(map);
    }

    @Test
    public void test5() {
        String s = users.stream()
                .map(UserBean::getName)     //分隔符=delimiter
                .collect(Collectors.joining("->"));
        System.out.println(s);
    }

    /**
     * 分组
     */
    //多级分组
    @Test
    public void test3() {
        Instant start = Instant.now();
        Map<Integer, Map<String, List<UserBean>>> map = users.stream()
                .collect(Collectors.groupingBy(UserBean::getAge, Collectors.groupingBy((u) -> {
                    if (u.getEmail() < 35) {
                        return "min";
                    }
                    return "max";
                })));
        System.out.println(map);
        Instant end = Instant.now();
        long l = Duration.between(start, end).toMillis();
        System.out.println(l);

    }

    @Test
    public void test7() {

        Integer[] is = new Integer[]{0, 1, 2, 3, 4, 5};
        for (int i = 0; i < is.length / 2; i++) {
            Integer tmp = is[i];
            is[i] = is[is.length - i - 1];
            is[is.length - i - 1] = tmp;
        }
        System.out.println(Arrays.toString(is));

        System.out.println(Math.round(11.4));
    }

    @Test
    public void test2() {
        //总数
        String s = users.stream()
                .map(UserBean::getName)
                .collect(Collectors.counting()).toString();
        System.out.println(s);
        //平均值
        Double i = users.stream()
                .collect(Collectors.averagingInt(UserBean::getAge));
        System.out.println(i);
        //分组
        Map<Integer, List<UserBean>> map = users.stream()
                .collect(Collectors.groupingBy(UserBean::getAge));
        System.out.println(map);
    }


    @Test
    public void test1() {
        users.stream()
                .map(UserBean::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        users.stream()
                .map(UserBean::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        users.stream()
                .map(UserBean::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }
    /**
     *   归约 （    参数类型均一致）
     *   Optional<T> reduce(BinaryOperator<T> accumulator);
     *
     *   双参数（有起始值）
     *  T reduce(T identity, BinaryOperator<T> accumulator);
     */
}
