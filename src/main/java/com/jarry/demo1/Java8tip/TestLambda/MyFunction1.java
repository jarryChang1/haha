package com.jarry.demo1.Java8tip.TestLambda;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip.TestLambda
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-24 17:20
 */
@FunctionalInterface
public interface MyFunction1<T, R> {
    R getValue(T t1, T t2);
}
