package com.jarry.demo1.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 11:54
 */
public class Json4Sql {
    public static boolean query(String json, String sql) {
        /*解析json*/
        json = json.replaceAll(" ", "");
        json = json.replaceAll("\t", "");
        json = json.replaceAll("\n", "");
        HashMap<String, String> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if ('}' == c) { //末尾
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.peek().equals("{")) {
                    stringBuilder.insert(0, stack.pop());//拼接字符串
                }
                stack.pop();
                list.add(stringBuilder.toString());
            } else if (c == ',') {  //拿出key和value
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.peek().equals("{")) {
                    stringBuilder.insert(0, stack.pop());//拼接字符串
                }
                list.add(stringBuilder.toString());
            } else {
                stack.push(json.substring(i, i + 1));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String first = list.get(i);//解析冒号
            int i1 = first.lastIndexOf(":");
            String before = first.substring(0, i1);
            String after = first.substring(i1+1);
            map.put(before.replaceAll("\"",""),after);
        }
        /*sql语法分析：
         * 1、用一位int来表示返回结果为true或者false；查询条件去|操作或者&操作他
         * 2、如果有or，则可以用or短路
         * 3、如果没有or，则必须都匹配成功*/
        int canMatch = 1;
        String[] split = sql.split(" ");
        ///每次取3个来判断
        int flag = -1;
        for (int i = 0; i < split.length; i+=4) {
            int tmpCanMatch = 0;
            String key = split[i];
            String op = split[i+1];
            String value = split[i+2];
            switch (op){
                case ">": if (Integer.valueOf(value) < Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                case "<": if (Integer.valueOf(value) > Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                case "=": if (value.equals(map.get(key))) tmpCanMatch = 1;
                case "like": if (map.get(key).contains(value.replaceAll("%","")))tmpCanMatch = 1;
            }
            if (flag == 1){
                canMatch |=tmpCanMatch;
            }else canMatch &=tmpCanMatch;

            if (split.length > 3+i ){
                if (split[i+3].equals("or")) {
                    flag = 1;
                }else flag = -1;
            }
        }
        return canMatch == 1;
    }
}
