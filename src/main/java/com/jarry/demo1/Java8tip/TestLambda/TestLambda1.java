package com.jarry.demo1.Java8tip.TestLambda;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.TestLambda
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-24 16:49
 */
public class TestLambda1 {
    List<UserBean> users = Arrays.asList(
            new UserBean("ss",22,22,33),
            new UserBean("rs",22,22,33),
            new UserBean("ts",22,22,33),
            new UserBean("qs",22,22,33)
    );

    @Test
    public  void test1(){
        Collections.sort(users,(u1,u2)->{
            if (u1.age == u2.age){
                return u1.name.compareTo(u2.name);
            }
            return Integer.compare(u1.age,u2.age);
        }
        );
        for (UserBean user:users
             ) {
            System.out.println(user);
        }
    }


    @Test
    public void test2(){
        //控制层
        Op(100L,200L,(x,y) -> x+y);

        BiFunction<Integer,Integer,Integer> biFunction = (x,y) -> x+y;
        Integer i =biFunction.apply(200,200);
        System.out.println(i);
    }
    //逻辑服务层
    public void Op(Long l1,Long l2,MyFunction1<Long,Long> myFunction1){
        System.out.println(myFunction1.getValue(l1,l2));
    }

}
