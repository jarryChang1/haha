package com.jarry.demo1.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.rabbitmq
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-17 10:51
 */
public class Receiver {
    private RabbitAdmin rabbitAdmin;
    private RabbitTemplate rabbitTemplate;
    private static String exchangeName = "extensionQueue";
    private static final String EXCHANGE_DELAY_BEGIN = "EXCHANGE_DELAY_BEGIN";
    private static final String QUEUE_DELAY_BEGIN = "QUEUE_DELAY_BEGIN";

    @Autowired
    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(String queueName, Object msg) {
        Queue queue = new Queue(queueName);
        TopicExchange exchange = new TopicExchange(exchangeName, true, true);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);

        rabbitTemplate.setExchange(exchangeName);
        rabbitTemplate.setQueue(queueName);
        rabbitTemplate.setRoutingKey(queueName);

        rabbitAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, queueName, new HashMap<>()));
        rabbitTemplate.convertAndSend(exchangeName, queueName, msg);
    }

    public void declare(String queueName)  {
        Queue queue=new Queue(queueName);
        TopicExchange exchange=new TopicExchange(exchangeName,true,true);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,queueName,new HashMap<>()));
    }

    public void send(String queueName,Object msg)  {
        rabbitTemplate.convertAndSend(exchangeName,queueName,msg);
    }

    public Object receive(String queueName) {
        return rabbitTemplate.receiveAndConvert(queueName);
    }

    /** 发送延时队列消息. */
    public void sendToDelayQueue(Object msg) {
        rabbitTemplate.convertAndSend(EXCHANGE_DELAY_BEGIN, QUEUE_DELAY_BEGIN, msg);
    }
}
