package com.jarry.demo1.Entry.Eunm;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @CreateTime: 2019-09-27 14:48
 * 简单枚举没有什么可说的，下面对自定义枚举进行简单总结：
 * <p>
 * 通过enum.values()获取枚举数组。
 * 通过enum.valueOf(name)获取枚举对象。
 * 通过Enum.valueOf(enumClass,name)获取枚举对象。
 * 设置枚举值其实调用的是全参数的构造函数。
 * 建议重写toString()方法，以便打印信息。
 */
@Slf4j
public class TestEunm {
    enum Fruit {
        APPLE, ORANGE, BANANA
    }

    @Test
    public void test1() {
        //测试简单枚举
        log.info("测试简单枚举");
        log.info("获取一个枚举值" + Fruit.class.toString() + " : " + Fruit.APPLE);
        //通过Fruit.values()获取枚举值数组
        for (Fruit fruit : Fruit.values()) {
            log.info("遍历枚举--" + fruit.getClass().toString() + " : " + fruit);
        }

    }

    enum CodeAndMessage {
        SUCCESS(1, "成功"),
        WARNING(0, "警告"),
        ERROR(-1, "报错");
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "CodeAndMessage{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }

        private CodeAndMessage(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getMessage(int code) {
            for (CodeAndMessage codeAndMessage : CodeAndMessage.values()
            ) {
                if (codeAndMessage.code == code) {
                    return codeAndMessage.message;
                }
            }
            return null;
        }

    }

    @Test
    public void test2() {
        log.info(CodeAndMessage.SUCCESS.getClass().toString() + " : " + CodeAndMessage.SUCCESS);
        for (CodeAndMessage codeAndMessage : CodeAndMessage.values()
        ) {
            log.info(codeAndMessage.getClass().toString() + " : " + codeAndMessage);
        }
        log.info(CodeAndMessage.getMessage(0));
        log.info(CodeAndMessage.ERROR.getCode() + "");

        //通过enum.valueOf(name)获取枚举对象
        log.info(CodeAndMessage.valueOf("WARNING") + "");
        //通过Enum.valueOf(enumClass,name)获取枚举对象:
        log.info(Enum.valueOf(CodeAndMessage.class, "SUCCESS") + "");
    }
}