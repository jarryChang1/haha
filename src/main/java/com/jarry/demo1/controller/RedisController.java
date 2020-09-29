package com.jarry.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 有点问题
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * =----------------------------------------------------------------------------------
 *
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-19 10:37
 */
@RestController
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    private JedisClusterConfig jedisClusterConfig;


    @GetMapping("/redis/get/{key}")
    private String get(@PathVariable("key") String key) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
//        redisTemplate.opsForZSet().add("Zset1",set);
//        System.out.println(redisProperties.toString());
        redisTemplate.opsForValue().set("jarry", "hahahahhaha");
        return redisTemplate.opsForValue().get(key).toString();


    }

//    @RequestMapping("redis/set/{key}/{value}")
//    private Boolean set(@PathVariable("key") String key,@PathVariable("value") String value){
//        boolean flag = true;
//        try{
//            redisTemplate.opsForValue().set(key,value);
//        }catch (Exception e){
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }


//    @PostMapping("/redis/setObject")
//    private String setObject(){
//        UserBean userBean = new UserBean("cch",12, 13, 99);
//        Jedis jedis = new Jedis("192.168.1.244",7000);
////        jedis.auth("hmzj");
//        jedis.set("jarry_test".getBytes(),RedisLoadTest.serialize(userBean));
//        return userBean.toString();
//    }

//    @Test
//    public void redisTransaction(){
//        //事务必须是同一个连接,此时开启操作都是单连接session
//        redisTemplate.execute(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//                redisOperations.multi();
//                redisOperations.opsForValue().set("name","zhangsan");
//                redisOperations.opsForValue().set("age","22");
//                String name = (String) redisTemplate.opsForValue().get("name");
//                System.out.println(name);
//                System.out.println(redisOperations.exec());
//                return redisOperations.exec();
//            }
//        });
//    }
}
