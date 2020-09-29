package com.jarry.demo1.Java8tip.MethodRef;

import com.jarry.demo1.Entry.UserBean;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * @CreateTime: 2019-09-24 18:03
 * 一、方法引用：若Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法格式：
 * <p>
 * 对象::实例方法名
 * <p>
 * 类::静态方法名
 * <p>
 * 类::实例方法名(若Lambda参数列表中第一个参数是 实例方法的调用者，且第二个参数是实例方法的参数时，可以使用)
 * <p>
 * 注意：1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * <p>
 * 二、构造器引用：
 * <p>
 * 格式：
 * ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 * <p>
 * 三、数组引用：
 * 格式：
 * Type::new
 */
public class TestMethodRef {
    //数组引用：
    @Test
    public void test7() {
        Function<Integer, String[]> function = x -> new String[x];
        String[] strings = function.apply(10);
        Function<Integer, String[]> function1 = String[]::new;
    }

    //构造器引用
    @Test
    public void test5() {
        Supplier<UserBean> supplier = () -> new UserBean();
/*        由于Supplier接口中抽象方法的参数列表与构造器的参数列表不一致
        Supplier<UserBean> supplier1 = (x,y,z,u) ->new UserBean(x,y,z,u);*/
        Supplier<UserBean> supplier2 = UserBean::new;
        UserBean u = supplier.get();
        System.out.println(u);
    }

    @Test
    public void test6() {
        Function<String, UserBean> function = (x) -> new UserBean(x);
        Function<String, UserBean> function1 = UserBean::new;
        BiFunction<String, Integer, UserBean> biFunction = UserBean::new;
        UserBean u = biFunction.apply("sss", 23);
        System.out.println(u);
    }

    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        boolean b = biPredicate1.test("sss", "ssss");
        System.out.println(b);
    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator1 = Integer::compare;
    }

    //对象::实例方法名
    @Test
    public void test1() {
        Consumer<String> consumer = x -> System.out.println(x);
        Consumer<String> consumer1 = System.out::println;
        consumer.accept("sssssssss");
    }

    @Test
    public void test2() {
        UserBean userBean = new UserBean();
        Supplier<String> supplier = () -> userBean.getName();
        String str = supplier.get();
        System.out.println(str);

        Supplier<Integer> supplier1 = userBean::getAge;
        Integer integer = supplier1.get();
        System.out.println(integer);
    }

}
