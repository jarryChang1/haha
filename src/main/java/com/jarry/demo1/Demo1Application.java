package com.jarry.demo1;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.jarry.demo1.aop.Service;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import java.io.IOException;

@ComponentScan(basePackages = "com.jarry.demo1.Config")
@ServletComponentScan(basePackages = "com.jarry.demo1.java3y.ProxyMode.Connector")
@MapperScan(basePackages = "com.jarry.demo1.mapper")
@SpringBootApplication
@EnableAsync
public class Demo1Application implements CommandLineRunner {

    public static volatile String str = "111000";

    public static void main(String[] args) throws SchedulerException, IOException {
//        try{
        SpringApplication.run(Demo1Application.class, args);
//        }catch (Exception e){e.printStackTrace();}
//        String json= "{\"pids\":[\"1\",\"2\",\"3\"]}";
//        System.out.println(new JsonParser().parse(json).getAsJsonObject().getAsJsonArray("pids").get(1).getAsString());
//        CronTrigger c=  Quartz.addOrUpdateQuartz();

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
//        Service service = ApplicationContextUtils.getBean(Service.class);
//        service.test();
    }

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration configuration = new Configuration();
        configuration.setPort(9092);
//        configuration.setHostname("localhost");
        configuration.setSocketConfig(new SocketConfig());
        configuration.setAuthorizationListener(handshakeData -> true);
        configuration.setWorkerThreads(100);
        return new SocketIOServer(configuration);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }


    @Override
    public void run(String... args) throws Exception {
        socketIOServer().start();
    }
}
