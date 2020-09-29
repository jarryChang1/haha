package com.jarry.demo1.Java8tip.StreamApi;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.StreamApi
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-26 09:58
 */
public class StreamApiPractice {
    List<UserBean> users = Arrays.asList(
            new UserBean("李红", 22, 22, 34),
            new UserBean("赵四", 36, 22, 46),
            new UserBean("王五", 22, 22, 33),
            new UserBean("张三", 58, 22, 47)
    );

    //用map、reduce数总数
    @Test
    public void test1() {
        System.out.println(users.stream()
                .map(e -> 1)
                .reduce(Integer::sum));
    }

    String[] words = new String[]{"Hello", "World"};


    @Test
    public void test2() {

        List<String[]> a1 = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        a1.forEach(System.out::print);


        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
    }
}
