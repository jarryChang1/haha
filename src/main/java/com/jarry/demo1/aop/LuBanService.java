package com.jarry.demo1.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.aop
 * @Author: Jarry.Chang
 * @CreateTime: 2020-06-01 11:37
 */
@Component
public class LuBanService {

    public LuBanService() {
        System.out.println("luBan create-------------");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new
                // config类主要完成对类的扫描
                AnnotationConfigApplicationContext(Config.class);
        Service service = ac.getBean(Service.class);
        service.test();
    }
}
