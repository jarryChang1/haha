package com.jarry.demo1.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.rabbitmq
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-22 15:23
 */
@Slf4j
@Component
@RabbitListener(queues = MqConfig.QUEUE_E)
public class Consumer {


    @RabbitHandler
    public void process(String content, Channel channel, Message message) throws Exception {
        try {
            Thread.sleep(3000);
            System.out.println("睡眠3秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        System.out.println("receiver success = " + content);

        log.info("接收处理队列E当中的消息： " + content);
    }

}