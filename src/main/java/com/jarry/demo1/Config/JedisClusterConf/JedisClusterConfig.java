//package com.jarry.demo1.Config.JedisClusterConf;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @BelongsProject: demo1
// * @BelongsPackage: com.jarry.demo1.Config.JedisClusterConf
// * @Author: Jarry.Chang
// * @CreateTime: 2020-04-02 14:19
// */
//@Configuration
//public class JedisClusterConfig {
//
//    private RedisProperties redisProperties;
//
//    @Autowired
//    public JedisClusterConfig(RedisProperties redisProperties){
//        this.redisProperties = redisProperties;
//    }
//
//    @Bean
//    public JedisCluster getJedisCluster(){
//        String[] serverArray = redisProperties.getClusterNodes().split(",");
//
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        for (String ipPort:serverArray
//             ) {
//            String[] ipPortPair = ipPort.split(":");
//
//            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
//
//        }
//        return new JedisCluster(nodes);
//    }
//
//}
