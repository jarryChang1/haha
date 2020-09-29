package com.jarry.demo1.controller;

import com.jarry.demo1.rabbitmq.MqConfig;
import com.jarry.demo1.rabbitmq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-21 17:36
 */
@Controller
@RequestMapping("/rabbitmq")
public class MyRabbitmqController {
    @Autowired
    public Sender sender;

    @RequestMapping("/sender")
    @ResponseBody
    public String sender() {
        System.out.println("send string:hello world");
        sender.sendMsg("jarry");
        sender.send2("发一个Q*", "Q.q");
        return "sending...";
    }
}