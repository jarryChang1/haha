//package com.jarry.demo1.Test;
//
//import com.jarry.demo1.Entry.UserBean;
//import redis.clients.jedis.Jedis;
//
//import java.io.*;
//import java.util.List;
//
///**
// * @BelongsProject: demo1
// * @BelongsPackage: com.jarry.demo1.Test
// * @Author: Jarry.Chang
// * @CreateTime: 2020-03-16 10:40
// */
//public class RedisLoadTest {
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.1.244",7002);
////        jedis.auth("hmzj");
//        byte[] bytes = jedis.get("jarry_test".getBytes());
//
//        Object obj=unserizlize(bytes); //转化为java对象
//        if(obj instanceof UserBean){
//            System.out.println(obj.toString());
//        }
//
//        jedis.close();
//    }
//    //序列化
//    public static byte [] serialize(Object obj){
//        ObjectOutputStream obi=null;
//        ByteArrayOutputStream bai=null;
//        try {
//            bai=new ByteArrayOutputStream();
//            obi=new ObjectOutputStream(bai);
//            obi.writeObject(obj);
//            byte[] byt=bai.toByteArray();
//            return byt;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //反序列化
//    public static Object unserizlize(byte[] byt){
//        ObjectInputStream oii=null;
//        ByteArrayInputStream bis=null;
//        bis=new ByteArrayInputStream(byt);
//        try {
//            oii=new ObjectInputStream(bis);
//            Object obj=oii.readObject();
//            return obj;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
