package com.example.app.ThreadAsyn.threadAsyn6;

import java.time.Instant;

/**
同步---异步传统方法的对比
 */
public class Mainclass {
    public static void main(String[] args) {
        long n = Instant.now().toEpochMilli();
        String s = Hello.threadAsynchSayHello();
        long f = Instant.now().toEpochMilli();
        System.out.println(s+"时间 :"+(f-n));

         n = Instant.now().toEpochMilli();
         s = Hello.threadsynchSayHello();
         f = Instant.now().toEpochMilli();
        System.out.println(s +"    ---时间 :"+(f-n));
    }
}
