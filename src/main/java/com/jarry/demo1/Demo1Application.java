package com.jarry.demo1;

import com.google.gson.JsonParser;
import com.jarry.demo1.quartz.Quartz;
import org.quartz.CronTrigger;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws SchedulerException{
        SpringApplication.run(Demo1Application.class, args);
        String json= "{\"pids\":[\"1\",\"2\",\"3\"]}";
        System.out.println(new JsonParser().parse(json).getAsJsonObject().getAsJsonArray("pids").get(1).getAsString());
        CronTrigger c=  Quartz.addOrUpdateQuartz();
    }

}
