package com.jarry.demo1.annotation;


import java.lang.annotation.*;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.annotation
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-27 15:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysOperationLog {

    String remark();
    /**
    1 增加，2删除，3修改，4导出。//查询、登录等
     */
    int operationType();
}
