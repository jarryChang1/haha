package com.jarry.demo1.Java8tip.TestLambda;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @CreateTime: 2019-09-25 10:52
 */
public class TestMethodRef1 {
    List<UserBean> users = Arrays.asList(
            new UserBean("ss",22,22,33),
            new UserBean("rs",22,22,33),
            new UserBean("ts",22,22,33),
            new UserBean("qs",22,22,33)
    );

    @Test
    public void test1(){
        Function<List<UserBean>,List<UserBean>> function =(x) ->{x.sort((u1, u2)->{
            if (u1.age == u2.age){
                return u1.name.compareTo(u2.name);
            }
            return Integer.compare(u1.age,u2.age);
        });
        return x;};
        function.apply(users);
        for (UserBean user:users
                ) {
            System.out.println(user);
        }

    }
    @Test
    public void test2(){
        String s= new String("sss");
        String l = s.toUpperCase();
        System.out.println(l);
        Function<String,String> function = (x) -> x.toUpperCase();
        String l1 = function.apply(s);
        System.out.println(l1);
    }
}
