package com.jarry.demo1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.annotation
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-02 16:47
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    int seconds();
    int maxCount();
    boolean needLogin() default true;
}
