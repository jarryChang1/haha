package com.jarry.demo1.Java8tip.Generics;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.TestLambda
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-27 09:50
 */
@Slf4j
public class javaGenericsClear {
    /**
     * <p>Title: 类型擦除之后的原始类型 示例</p>
     */
    @Data
    static class TempList<T>{
        private T t;

        public T setT(T t) {
            this.t = t;
            return t;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //类型擦除之后的原始类型
        System.out.println();
        log.info("泛型类型擦除之后的原始类型:");
        TempList<Integer> tempList = new TempList<Integer>();
        log.info(tempList.getClass().getDeclaredField("t").getType().toString());
        Method[] methods = tempList.getClass().getDeclaredMethods();
        for (Method method : methods) {
            log.info(method.getReturnType() + " " + method.getName() + " (" + method.getParameterTypes()[0] + ")");
            log.info("通过反射跳过类型检查：");
            TempList<Double> doubleMyGenericsType1 = new TempList<Double>();
//类型检查通过
            doubleMyGenericsType1.setT(new Double(2D));
//类型检查不通过
//doubleMyGenericsType.setT(new Integer(1));
//通过反射跳过类型检查
            doubleMyGenericsType1.getClass().getMethod("setT",Object.class).invoke(doubleMyGenericsType1,new Double(1));
            log.info(doubleMyGenericsType1.getT().toString());
        }
//        //类型擦除与编译前检查
//        log.info("类型擦除与编译前检查:");
//        TempList<Integer> integerMyGenericsType1 = new TempList<Integer>();
////类型检查通过
//        integerMyGenericsType1.setT(new Integer(1));
////类型检查不通过，在IDE中报错
//        integerMyGenericsType1.setT(new Double(2D));

//通过反射跳过类型检查

    }


}
