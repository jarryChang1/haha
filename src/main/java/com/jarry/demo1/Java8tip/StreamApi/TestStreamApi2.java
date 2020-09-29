package com.jarry.demo1.Java8tip.StreamApi;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @CreateTime: 2019-09-25 12:16
 */
public class TestStreamApi2 {

    List<UserBean> users = Arrays.asList(
            new UserBean("李红", 22, 22, 33),
            new UserBean("赵四", 36, 22, 33),
            new UserBean("王五", 22, 22, 33),
            new UserBean("张三", 58, 22, 33)
    );

    //Comparator<T>接口
    //int compare(T o1, T o2);
    //实现它的compare接口，传入2个参数，返回一个int型。
    @Test
    public void test5() {

        users.stream()
                .sorted((x, y) -> {
                    if (x.age == y.age) {
                        return x.name.compareTo(y.name);
                    }
                    return Integer.compare(x.age, y.age);
                }).forEach(System.out::println);
    }

    /**
     * 映射：
     * map => 接收一个Lambda ，将元素转换成其它形式或提取信息。接
     * 收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * <p>
     * flatMap => 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        list.stream()
                .map(TestStreamApi2::filterCharacter)
                .forEach(s -> s.forEach(System.out::println));

        list.stream()
                .flatMap(TestStreamApi2::filterCharacter)
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()
        ) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void test1() {
        //中间操作：没有终止操作前（中间操作）不会执行
        Stream<UserBean> stream = users.stream().filter(x -> {
            System.out.println("StreamApi中间操作执行中");
            return x.age > 35;
        });
        //终止操作：一次性执行全部内容（“惰性求值”）
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        users.stream().filter(x -> {
            System.out.println("短路!");
            return x.age > 35;
        })
                .limit(1)
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        users.stream()
                .skip(1)
                .distinct()
//                .filter(x -> x.age>35)
                .forEach(System.out::println);

    }
}
