package com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter;

import javax.servlet.http.*;
import java.io.Serializable;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ListenerAndFilter
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-28 15:01
 */
public class HttpSessionBindingListener implements javax.servlet.http.HttpSessionBindingListener, HttpSessionActivationListener, Serializable
        , HttpSessionListener {

    //需要反序列时，这个不变的序列化ID就会派上用场（不会因为属性、字段增多、变化而变化）
    private static final long serialVersionUID = 7633008237190687846L;

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        System.out.println("httpSession钝化了!");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        System.out.println(httpSession.getId() + "httpSession活化了!");
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("绑定了对象!");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("解绑了对象!");

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        /*通过session创建来计数，统计连接服务器的人数*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
