package com.jarry.demo1.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.aop
 * @Author: Jarry.Chang
 * @CreateTime: 2020-06-01 11:37
 */
@Component
public class Service {
    private LuBanService luBanService;

    public Service() {
        System.out.println("service create by no args constructor");
    }

    // 通过Autowired指定使用这个构造函数，否则默认会使用无参
    @Autowired
    public Service(LuBanService luBanService) {
        System.out.println("注入luBanService by constructor with arg");
        this.luBanService = luBanService;
        System.out.println("service create by constructor with arg");
    }
    public void test(){
        System.out.println(luBanService);
    }

}