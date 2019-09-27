package com.jarry.demo1.Java8tip.Generics;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
/**
 * @CreateTime: 2019-09-27 10:37
 * Java泛型的设计初衷：降低类型转换的安全隐患，而非为了实现任意化。
 * Java泛型的实现原理：类型擦除。
 */
public class Generics8Limits {
//    List<int> list = new List<int>();// 编译前类型检查报错(1不能使用基础类型)
//    <T> void test(T t){
//        t = new T();//编译前类型检查报错（2不允许实例化）
//    }
    /**
     * <p>Title: 3.Java泛型不允许进行静态化</p>
     */
//    static class Demo<T>{
//        private static T t;// 编译前类型检查报错
//
//        // 3编译前类型检查报错（静态化）
//        public static T getT() {
//            return t;
//        }
//    }
    static class Demo<T>{
        private  T t;// 编译前类型检查报错

         Demo(T t){
            this.t =t;
        }
        // 编译前类型检查报错
        public  T getT() {
            return t;
        }
    }
    /**
     * <p>Title: 8.Java泛型不允许作为参数进行重载</p>
     */
    static class Demo8<T>{
        void test(List<Integer> list){}
        //不允许作为参数列表进行重载
        //void test(List<Double> list){}
    }

    List<Integer> integerList = new ArrayList<Integer>();
    List<Double> doubleList = new ArrayList<Double>();
    //4不能直接进行类型转换，类型检查报错
    //integerList = doubleList;

    static void cast(List<?> orgin, List<?> dest){
        dest = orgin;
    }
    public static void main(String[] args){
        List<Integer> integerList = new ArrayList<Integer>();
        List<Double> doubleList = new ArrayList<Double>();
        //通过通配符进行类型转换
        cast(doubleList,integerList);


        List<String> stringList = new ArrayList<String >();
        //5不能直接使用instanceof，类型检查报错
        //log.info(stringList instanceof ArrayList<Double>);
        //通过通配符实现运行时验证
        log.info(""+(stringList instanceof ArrayList<?>));

        //易读出,肯定是返回类型的子类型，可以通过向上转型成功获取
        //
        List<? extends Number> ls = new ArrayList<Integer>();
        ls = Arrays.asList(new Integer(1),new Double(2.0),new Long(3L));
        Number n= ls.get(0);
        /**
         *   因为可以确定父类型，所以可以以父类型去获取数据（向上转型）。但是不能写入数据。
         */
//        ls.add(new Integer(2));
        //写Number就很确定，易读出
        Integer i = (Integer) ls.get(0);
        ls.contains(1);
        System.out.println(ls);

        //容易写入，写Number的子类就ok，
        List<? super Number> list = new ArrayList<Number>();
        list.add(1);
        list.add(1.0);
        list.add(4L);
        Object number = list.get(0);
        //(super)java中不允许读数据
//        Number e = list.get(0);

        Object o = new Object();

        for (Object demo:list
                ) {
            log.info(demo.getClass().toString() + " : " + demo.toString());
        }
        System.out.println(list);

        //6（泛型不能创建不定型数组）
        Demo<Integer>[] demos = new Demo[2];
        demos[0] = new Demo<>(122);
        demos[1] = new Demo<>(123);
        for (Demo demo:demos
                ) {
            log.info(demo.getT().getClass().toString() + " : " + demo.t);
        }

        //6通过通配符实现泛型数组
        Demo<?>[] iDemo = new Demo<?>[2];
        iDemo[0] = new Demo<Integer>(1);
        iDemo[1] = new Demo<>("lalala");
        for (Demo demo:iDemo
             ) {
            log.info(demo.getT().getClass().toString() + " : " + demo.t);
        }
    }

}
