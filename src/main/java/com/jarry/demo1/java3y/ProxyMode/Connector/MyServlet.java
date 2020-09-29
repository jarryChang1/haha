package com.jarry.demo1.java3y.ProxyMode.Connector;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet是单例的
 */
/*
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.Connector
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 16:33
 */
public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        /**   第一次访问；我们发现init()和service()都被调用了*/
        System.out.println("加载Servlet。当Tomcat第一次访问Servlet的时候，Tomcat会负责创建Servlet的实例;调用init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //调一次 输出一次
        System.out.println("处理服务。浏览器访问Servlet时，调用service");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        //关闭
        System.out.println("销毁。tomcat关闭 或意外删除tomcat（如:长时间不使用），调用destroy");
    }
}
