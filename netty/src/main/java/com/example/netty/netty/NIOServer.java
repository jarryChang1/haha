package com.example.netty.netty;

import java.nio.IntBuffer;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.netty.netty
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-27 12:28
 */
public class NIOServer {
    public static void main(String[] args) {
        //举例说明buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        int[] s ={10,11,10,13,10};
        intBuffer.put(s);
        int i = intBuffer.get(1);
        System.out.println(i);
        intBuffer.flip();//转换“读取”模式与“写入”模式，执行后intBuffer的 position 会置为0
        System.out.println(intBuffer.get(0)+intBuffer.get(2));
        int [] ints = intBuffer.array();
        System.out.println(ints.toString());
    }
}
