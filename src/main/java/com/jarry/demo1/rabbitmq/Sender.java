package com.jarry.demo1.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
    简单rabbitmq练手
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-17 10:50
 */
@Component
public class Sender {
//    private RabbitAdmin rabbitAdmin;

    @Autowired
    private AmqpTemplate rabbitTemplate;
////    private static String exchangeName = "taskDateExchange";
////    private static final String EXCHANGE_DELAY_BEGIN = "EXCHANGE_DELAY_BEGIN";
////
////    @Autowired
////    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
////        this.rabbitAdmin = rabbitAdmin;
////    }
////
////    @Autowired
////    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
////        this.rabbitTemplate = rabbitTemplate;
////    }
////    public void  sendMsg(String queueName,Object msg){
////        Queue queue = new Queue(queueName);
////        TopicExchange exchange = new TopicExchange(exchangeName,true,true);
////        rabbitAdmin.declareExchange(exchange);
////        rabbitAdmin.declareQueue(queue);
////        rabbitAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,queueName,new HashMap<>()));
////
////
////        rabbitTemplate.setExchange(exchangeName);
////        rabbitTemplate.setQueue(queueName);
////        rabbitTemplate.setRoutingKey(queueName);
////        rabbitTemplate.convertAndSend(exchangeName,queueName,msg);
////    }
////
////    public void declare(String queueName){
////        Queue queue = new Queue(queueName);
////        TopicExchange exchange = new TopicExchange(exchangeName,true,true);
////        rabbitAdmin.declareExchange(exchange);
////        rabbitAdmin.declareQueue(queue);
////        rabbitAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,queueName,new HashMap<>()));
////
////    }
////    public boolean delete(String queueName){
////        return rabbitAdmin.deleteQueue(queueName);
////    }

    public void send(String queueName,Object msg){
        rabbitTemplate.convertAndSend(queueName,msg);
    }

//    public void purge(String queueName){ rabbitAdmin.purgeQueue(queueName,true);}
//



}
