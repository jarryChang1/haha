package com.jarry.demo1.controller;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-19 14:57
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpUtils {

    static RestTemplate restTemplate;

    static {
        if (null == restTemplate) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setReadTimeout(15000);
            factory.setConnectTimeout(15000);

            restTemplate = new RestTemplate(factory);
        }
    }

    //    public static void main(String[] args) {
//        Jedis jedis = new Jedis("114.115.167.79",8001);
//        jedis.auth("12345qwer890-iop[");
//        Set<String> set = jedis.keys("user*");
//        System.out.println(set.toString());
//    }
    public static Object getRequest(String url, Class clazz) throws RestClientException {
        ResponseEntity entity = restTemplate.getForEntity(url, clazz);
        return entity.getBody();
    }

    public static Object sendRequest(String url, HttpMethod httpMethod, Map<String, Object> params, Class clazz) throws RestClientException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity entity = restTemplate.exchange(url, httpMethod, httpEntity, clazz);
        return entity.getBody();
    }
}