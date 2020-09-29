package com.jarry.demo1.java3y.ProxyMode.Collection;

import com.jarry.demo1.Entry.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.Collection
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-11 11:02
 * <p>
 * <p>
 * <p>
 * <p>
 * 因为HashMap中的key与原来的对象是同一个地址，所以在map中拿出来修改后，原来的key也被修改了。
 * 索引map中不会包含已经修改过的key。
 * <p>
 * 1、也就是说，中途改变了map对象的key，key的hashCode； 但此Node仍然在原来放置的hash桶上面存储。
 * 2、如果使用原来对象的key来查询，才能查询到。
 * 3、map只能通过原来的key去获取修改过的对象。
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap(16);
        String s = "djjfas";
        Message message = new Message();
        message.setMeassage(s);
        //构造key
        Message key = new Message();
        key.setMeassage(s);

        map.put(key, message);//put进去key和value
        System.out.println(key.hashCode());
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry e :
                set) {

            Message message1 = (Message) e.getKey();
            message1.setMeassage("changed");
            System.out.println(key.hashCode());
            System.out.println(map.containsKey(message1));
            System.out.println(map.containsKey(key));//因为HashMap中的key与原来的对象是同一个地址，所以在map中拿出来修改后，原来的key也被修改了。
            System.out.println(map.get(key));
            System.out.println(map.get(message1));
        }
        key.setMeassage(s);
        System.out.println(map.containsKey(key)); //map只能通过原来的key去获取修改过的对象。
        Arrays.stream(map.entrySet().toArray()).forEach(System.out::println);

    }
}
