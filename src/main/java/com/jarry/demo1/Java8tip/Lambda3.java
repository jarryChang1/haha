package com.jarry.demo1.Java8tip;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @CreateTime: 2019-09-24 16:45
 *
 *Java8内置的四大核心函数式接口（典型场景）
 *
 *    Consumer<T> :消费型接口（输出）
 *      void accept（T t）;
 *      T是参数类型，实现一个动作
 *
 *    Supplier<T> :供给型接口（获值）
 *      T  get();
 *      T是返回值类型
 *
 *    Function<T,R> : 函数型接口（构造器）
 *      R apply(T t);
 *      T是参数，R是返回值
 *
 *    Predicate<T> : 断言型接口（判等）
 *      Boolean test(T t);
 *      T是参数类型，返回布尔值
 */
public class Lambda3 {
    public void accept(long money, Consumer<Long> consumer){
        consumer.accept(money);
    }
    /**
     * 消费
     */
    @Test
    public void consumer(){
        accept(100,(e)-> System.out.println("消费了"+e+"元"));
    }





    public Integer get(Supplier<Integer> supplier){
        return supplier.get();
    }
    /**
     * 供给
     */
    @Test
    public void supplier(){
        int value = get(()-> (int)(Math.random()*100));
        System.out.println(value);
    }





    public String apply(String source, Function<String, String> func){
        return func.apply(source);
    }
    /**
     * 函数
     */
    @Test
    public void function(){
        String source = "cch";
        String result = apply(source, (k) -> k.toUpperCase());
        System.out.println(result);
    }





    public boolean test(UserBean student, Predicate<UserBean> predicate){
        return  predicate.test(student);
    }
    /**
     * 断言
     */
    @Test
    public void predicate(){
        boolean result = test(new UserBean("cch",12, 13, 99), (e) -> e.getAge() > 10);
        System.out.println(result);
    }
}
