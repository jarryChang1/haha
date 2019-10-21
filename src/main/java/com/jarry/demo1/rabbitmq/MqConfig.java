package com.jarry.demo1.rabbitmq;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.rabbitmq
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-17 10:46
 */
@Configuration
public class MqConfig {
//    private static final int QUEUE_EXPIRATION = 8000;  // 队列延时(过期)时间
//    private static final String EXCHANGE_DELAY_BEGIN = "EXCHANGE_DELAY_BEGIN";
//    private static final String EXCHANGE_DELAY_DONE = "EXCHANGE_DELAY_DONE";
//    private static final String QUEUE_DELAY_BEGIN = "QUEUE_DELAY_BEGIN";
//    private static final String QUEUE_DELAY_DONE = "QUEUE_DELAY_DONE";
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//    @Value("${spring.rabbitmq.port}")
//    private  int port;
//    @Value("${spring.rabbitmq.host}")
//    private  String userHost;
//
//    @Bean(value = "connectRabbitmq")
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//        cachingConnectionFactory.setVirtualHost("/");
//        cachingConnectionFactory.setHost(userHost);
//        return cachingConnectionFactory;
//    }
//    @Bean
//    public RabbitAdmin rabbitAdmin(@Qualifier("connectRabbitmq")ConnectionFactory connectionFactory){
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    private static final int DEFAULT_CONCURRENT = 100;
//
//    private static final int DEFAULT_PREFETCH_COUNT = 50;
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer containerFactoryConfigurer, @Qualifier("connectRabbitmq")ConnectionFactory connectionFactory){
//            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//            factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
//            factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
//            containerFactoryConfigurer.configure(factory,connectionFactory);
//            return factory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(){ return new RabbitTemplate(connectionFactory());}

    @Bean(name= "testQueue1")
    public Queue testQueue1() {
        return new Queue("testQueue1");
    }



}

