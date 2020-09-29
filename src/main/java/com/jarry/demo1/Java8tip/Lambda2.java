package com.jarry.demo1.Java8tip;

import com.jarry.demo1.Entry.UserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-24 15:03
 */
public class Lambda2 {
    public static void checkAndExecute(List<UserBean> list, Predicate<UserBean> predicate,
                                       Consumer<UserBean> consumer) {
        for (UserBean u : list
        ) {
            if (predicate.test(u))
                consumer.accept(u);
        }
    }

    List<UserBean> guiltyPersons = new ArrayList<>();

    public void main() {
        guiltyPersons.add(new UserBean());
        checkAndExecute(guiltyPersons, p -> p.getName().startsWith("Z"), p -> System.out.println(p.getEmail()));

        /**
         *
         * 最简洁的写法
         */
        guiltyPersons.stream().filter(p -> p.getName().startsWith("Z")).forEach(p -> System.out.println(p.getEmail()));
        guiltyPersons.stream().filter(p -> p.getName().startsWith("Z")).forEach(System.out::println);
        guiltyPersons.stream().filter(p -> p.getName().startsWith("Z")).collect(Collectors.toList());
    }
}
