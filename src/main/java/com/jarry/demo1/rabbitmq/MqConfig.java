package com.jarry.demo1.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.port}")
    private  int port;
    @Value("${spring.rabbitmq.host}")
    private  String userHost;

    @Bean(value = "connectRabbitmq")
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost("/");
        cachingConnectionFactory.setHost(userHost);
        return cachingConnectionFactory;
    }
    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";
    public static final String QUEUE_D = "QUEUE_D";
    public static final String QUEUE_E = "QUEUE_E";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "Q.#";
    public static final String ROUTINGKEY_C = "Q.*";//.为单词分隔符，*号匹配一个单词，#号匹配多个单词（或0个）


//    @Bean
//    public RabbitAdmin rabbitAdmin(@Qualifier("connectRabbitmq")ConnectionFactory connectionFactory){
//        return new RabbitAdmin(connectionFactory);
//    }
        //消费者数量，默认100
//      private static final int DEFAULT_CONCURRENT = 100;
        //每个消费者获取最大的投递数50
//      private static final int DEFAULT_PREFETCH_COUNT = 50;
//
    @Bean
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer containerFactoryConfigurer, @Qualifier("connectRabbitmq")ConnectionFactory connectionFactory){
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//            factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
//            factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
            containerFactoryConfigurer.configure(factory,connectionFactory);
            return factory;
    }
//
    @Bean
    public AmqpTemplate rabbitTemplate(){ return new RabbitTemplate(connectionFactory());}

    //直连交换机directexchange精确匹配到队列；
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }
    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }
    @Bean
    public Binding binding() {

        return BindingBuilder.bind(queueA())
                            .to(defaultExchange())
                            .with(MqConfig.ROUTINGKEY_A);
    }

//    @Bean(name= "testQueue1")
//    public Queue testQueue1() {
//        return new Queue("testQueue1");
//    }

    /**
     * 广播模式
     * （一个队列一个消费者）
     * @return
     */
    //建立广播交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_B);
    }
    //创建2个队列
    @Bean
    public Queue queueB() { return new Queue(QUEUE_B, true); }//队列持久

    @Bean public Queue queueC() { return new Queue(QUEUE_C, true); }//队列持久
    //绑定2个队列至交换机上
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queueB())
                .to(fanoutExchange());
    }
    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queueC())
                .to(fanoutExchange());
    }
    /**
     *路由（模糊匹配）
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_C);
    }
    @Bean
    public Queue queueD() { return new Queue(QUEUE_D, true); }//队列持久

    @Bean
    public Queue queueF(){
        return QueueBuilder.durable("queue_f")
                .withArgument("x-overflow","reject-publish")//drop-head、reject-publish或reject-publish-dlx
                .build();
    }

    @Bean public Queue queueE() { return new Queue(QUEUE_E, true); }//队列持久

    @Bean
    public Binding binding4() {
        return BindingBuilder.bind(queueD())
                .to(topicExchange())
                .with(ROUTINGKEY_B);
    }
    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queueE())
                .to(topicExchange())
                .with(ROUTINGKEY_C);
    }

}

