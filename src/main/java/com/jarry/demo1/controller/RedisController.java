package com.jarry.demo1.controller;

import com.jarry.demo1.Entry.UserBean;
import com.jarry.demo1.Test.RedisLoadTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;


/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-19 10:37
 */
@Component
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis/get/{key}")
    private String get(@PathVariable("key") String key){
        return redisTemplate.opsForValue().get(key).toString();
    }

    @RequestMapping("redis/set/{key}/{value}")
    private Boolean set(@PathVariable("key") String key,@PathVariable("value") String value){
        boolean flag = true;
        try{
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    @PostMapping("/redis/setObject")
    private String setObject(){
        UserBean userBean = new UserBean("cch",12, 13, 99);
        Jedis jedis = new Jedis("192.168.1.244",6379);
        jedis.auth("hmzj");
        jedis.set("jarry_test".getBytes(),RedisLoadTest.serialize(userBean));
        return userBean.toString();
    }

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
