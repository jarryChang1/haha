package com.jarry.demo1.Java8tip.Generics;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-26 17:10
 */
public class JavaGenerics {
    /**
     * 泛型类语法示例
     *
     * MyGenericsType：泛型类名，即泛型原始类型
     * <T>：泛型标识，标识这是一个泛型类或者泛型方法
     * T：泛型类型
     * t：泛型类型的实例对象
     */
    public class MyGenericsType<T extends Number> {
        private T t;

        /**
         * <p>Title: 这是一个普通方法</p>
         */
        public T getT() {
            return t;
        }

        /**
         * <p>Title: 这是一个泛型方法</p>
         */
        public <T> T getTT() {
            T t = null;
            return t;
        }
    }

    @Test
    public void test1(){
            //泛型原始类型
            MyGenericsType myGenericsType = new MyGenericsType();
            log.info(myGenericsType.getClass().toString());
//类型擦除
        /**泛型在编译之后，只保留了原始类型，即MyGenericsType。
         * 泛型类型擦除：泛型在编译阶段，生成的字节码中不包含泛型类型，只保留原始类型。
         */
        log.info("类型擦除:");
        MyGenericsType myGenericsType1 = new MyGenericsType();
        MyGenericsType<Integer> integerMyGenericsType = new MyGenericsType<Integer>();
        MyGenericsType<Double> doubleMyGenericsType = new MyGenericsType<Double>();
        log.info(myGenericsType1.getClass().toString());
        log.info(integerMyGenericsType.getClass().toString());
        log.info(doubleMyGenericsType.getClass().toString());
            //泛型类型
            MyGenericsType<Integer> integerMyGenericsType2 = new MyGenericsType<Integer>();
            log.info(integerMyGenericsType2.getClass().toString());
    }



}
