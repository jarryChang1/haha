package com.jarry.demo1.aop;

import com.alibaba.fastjson.JSONObject;
import com.jarry.demo1.Entry.OperationLog;
import com.jarry.demo1.annotation.SysOperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.aop
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-27 15:24
 */
@Aspect
@Component
public class SysLogAspects {

    @Before(value = "execution(* com.jarry.demo1.controller.*.*(..))")
    public void saveSysLog(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String describe = "";
        int operationType = 0;
        SysOperationLog log = method.getAnnotation(SysOperationLog.class);
        if (log != null){
            describe = log.remark() != null ? log.remark():"未知";
            operationType = log.operationType() != 0 ? log.operationType() : 0;
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        //请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        //servletRequest、servletResponse,需排除，否则java.lang.IllegalStateException:
        // 0还有mutipartFile没用。
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        //参数数组转换为json.同时可以过滤掉无用参数
        String params = "";
        if (arguments != null){
            params = JSONObject.toJSONString(arguments);
        }
        //拿到用户的信息
        OperationLog operationLog = new OperationLog();
        operationLog.setId(1L);
        operationLog.setCreateTime(new Date());
        operationLog.setMethod(className+"."+methodName);
        operationLog.setOperationDescribe(describe);
        operationLog.setOperationType(operationType);
        operationLog.setParams(params);
        System.out.println(operationLog.toString());
    }

}
