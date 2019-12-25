package com.jarry.demo1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-19 10:37
 */
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

    @Test
    public void redisTransaction(){
        //事务必须是同一个连接,此时开启操作都是单连接session
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                redisOperations.opsForValue().set("name","zhangsan");
                redisOperations.opsForValue().set("age","22");
                String name = (String) redisTemplate.opsForValue().get("name");
                System.out.println(name);
                System.out.println(redisOperations.exec());
                return redisOperations.exec();
            }
        });
    }
}
