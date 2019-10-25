package com.jarry.demo1;

import com.google.gson.JsonParser;
import com.jarry.demo1.Test.BBossESStarterTestCase;
import com.jarry.demo1.quartz.Quartz;
import org.quartz.CronTrigger;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@ComponentScan
@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws SchedulerException, IOException {
        SpringApplication.run(Demo1Application.class, args);
        String json= "{\"pids\":[\"1\",\"2\",\"3\"]}";
        System.out.println(new JsonParser().parse(json).getAsJsonObject().getAsJsonArray("pids").get(1).getAsString());
        CronTrigger c=  Quartz.addOrUpdateQuartz();



//        BBossESStarterTestCase bBossESStarterTestCase = new BBossESStarterTestCase();
//       try {
//           bBossESStarterTestCase.testBbossESStarter();
//       }catch (Exception e){
//          e.printStackTrace();
//       }

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("114.115.167.79", 9300, "http")));
//        System.out.println(client.toString());
//        client.close();

    }

}
