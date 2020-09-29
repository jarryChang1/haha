package com.jarry.demo1.Java8tip;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @CreateTime: 2019-09-24 14:15
 * <p>
 * 以前用的是匿名内部类来实现特殊功能，现在采用Lambda将一个方法（“一块代码”）赋值给一个java变量，以实现特殊功能
 * 省去了（实现接口 、重写方法等复杂操作）
 * <p>
 * <p>
 * lambda箭头->
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所执行的功能，即Lambda体
 * <p>
 * <p>
 * 1、语法格式1：无参数，无返回值
 * () -> System.out.println("hello Lambda!");
 * 2、语法格式2：有一个参数，无返回值
 * x->System.out.println(x);
 * 3、语法格式3：有多个参数，有返回值，且lambda体中有多条语句
 * Comparator<Integer> comparator = (x,y)->{
 * System.out.println("函数式接口");
 * return Integer.compare(x,y);
 * };
 * 4、语法格式4：若lambda体中只有一条语句，return和大括号都可省略
 * Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
 */
public class Lambda1 implements MyLambdaInterface {

    MyLambdaInterface aBlockOfCode = s -> System.out.println(s);

    @Override
    public void doSomething(String s) {
        System.out.println(s);

    }

    List<String> guiltyPersons = new ArrayList<>();

    public MyLambdaInterface getaBlockOfCode() {
        return aBlockOfCode;
//        guiltyPersons.stream().filter(s->s.startsWith("z")).collect(Collectors.toList());
    }

    public void setaBlockOfCode(MyLambdaInterface aBlockOfCode) {
        this.aBlockOfCode = aBlockOfCode;
    }

    public static void enact(MyLambdaInterface myLambdaInterface, String s) {
        myLambdaInterface.doSomething(s);
    }

    /**
     * ----------------------------------------------------------------
     */
    @Test
    public void sss() {
        enact(s -> System.out.println(s), "sssss");

        MyLambdaInterface myLambdaInterface = new Lambda1();
        enact(myLambdaInterface, "ssssssss");
        Class<?> c = Number.class;
        System.out.println(c.getSuperclass());

    }

    @Test
    public void test1() {
//        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        comparator.compare(1, 2);
        System.out.println(comparator.compare(1, 2));
    }
}
