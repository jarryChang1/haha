package com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-28 11:52
 */
public class MVCListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    public MVCListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("容器创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("容器销毁了");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("请求销毁了");

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("请求创建了");
        //web中 在RequestContextHolder中设置attributes。
        // 在LocaleContextHolder中设置locale

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session创建了");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session销毁了");

    }
}
