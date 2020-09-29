package com.jarry.demo1.Test;

import java.util.*;

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
            String after = first.substring(i1 + 1);
            map.put(before.replaceAll("\"", ""), after);
        }
        /*sql语法分析：
         * 1、用一位int来表示返回结果为true或者false；查询条件去|操作或者&操作他
         * 2、如果有or，则可以用or短路
         * 3、如果没有or，则必须都匹配成功*/
        int canMatch = 1;
        String[] split = sql.split(" ");
        ///每次取3个来判断
        int flag = -1;
        for (int i = 0; i < split.length; i += 4) {
            int tmpCanMatch = 0;
            String key = split[i];
            String op = split[i + 1];
            String value = split[i + 2];
            switch (op) {
                case ">":
                    if (Integer.valueOf(value) < Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                case "<":
                    if (Integer.valueOf(value) > Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                case "=":
                    if (value.equals(map.get(key))) tmpCanMatch = 1;
                case "like":
                    if (map.get(key).contains(value.replaceAll("%", ""))) tmpCanMatch = 1;
            }
            if (flag == 1) {
                canMatch |= tmpCanMatch;
            } else canMatch &= tmpCanMatch;

            if (split.length > 3 + i) {
                if (split[i + 3].equals("or")) {
                    flag = 1;
                } else flag = -1;
            }
        }
        return canMatch == 1;
    }

    public static boolean query4(String json, String sql) {
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
            String after = first.substring(i1 + 1);
            map.put(before.replaceAll("\"", ""), after);
        }

        /**sql解析*/
        int canMatch = 0;
        OrParser orParser = new OrParser(map);
        AndParser andParser = new AndParser(map);
        if (sql.contains("or")) {
            String[] ors = sql.split("or");
            for (int i = 0; i < ors.length; i++) {

                if (ors[i].contains("and")) {
                    String[] ands = ors[i].split("and");
                    for (int j = 0; j < ors.length; j++) {
                        System.out.println(ands[j]);
                        String andSql = ands[j];
                        canMatch = andParser.doParse(andSql, 1);
                        //使用or的方式去Parse;
                        //如果解析完后值为false,直接返回
                    }
                    if (canMatch == 1) return true;
                } else {
                    canMatch = orParser.doParse(ors[i], canMatch);
                }
            }
        }//如果有or的情况下,一次解析数组长度最小的sql，如果有一个true，则返回true;
        else {
            //如果没有or的情况下,解析and的每个,加入优先级队列。
            //如果一个解析失败，则返回false;
            String[] ands = sql.split("and");
//            OrParser orParser = new OrParser(map);
            PriorityQueue queue = new PriorityQueue();
            for (int i = 0; i < ands.length; i++) {
//                if (ands[i].contains("or")){
//                    String[] ors = ands[i].split("or");
//                    for (int j = 0; j < ors.length; j++) {
//                        System.out.println(ors[j]);
//                        String orSql = ors[j];
//                        canMatch = orParser.doParse(orSql, 0);
//                        //使用or的方式去Parse;
//                        //如果解析完后值为false,直接返回
//                    }
//                    if (canMatch == 0) return false;
//                }
                if (ands[i].contains("=")) {
                    wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 2);
                    queue.add(wrappedExpression);
                } else if (ands[i].contains(">")) {
                    wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 1);
                    queue.add(wrappedExpression);
                } else if (ands[i].contains("<")) {
                    wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 1);
                    queue.add(wrappedExpression);
                } else if (ands[i].contains("like")) {
                    wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 0);
                    queue.add(wrappedExpression);
                }
                //根据String.contains() = 、> 、<、like来设置优先级，加入优先级队列。
            }
            //从PriorityQueue中取出去用AndParser解析;
            while (!queue.isEmpty()) {
                wrappedExpression remove = (wrappedExpression) queue.remove();
                canMatch = andParser.doParse(remove.getSqlExpression(), canMatch);
                if (canMatch == 0) return false;
//            System.out.println(remove.toString());
            }
        }
        return canMatch == 1;
    }


//    public static void main(String[] args) {
//        String s = "sql = 1 and a = q or p = 9 and m = c and p like jjd";
//        String[] ands = s.split("and");
//        HashMap<String, String> map = new HashMap<>();
//        OrParser orParser = new OrParser(map);
//        PriorityQueue queue  = new PriorityQueue();
//        int canMatch = 0;
//        for (int i = 0; i < ands.length; i++) {
//            if (ands[i].contains("or")){
//                String[] ors = ands[i].split("or");
//                for (int j = 0; j < ors.length; j++) {
//                    System.out.println(ors[j]);
//                    String orSql = ors[j];
//                    canMatch = orParser.doParse(orSql, 0);
//                    //使用or的方式去Parse;
//                    //如果解析完后值为false,直接返回
//                }
//                if (canMatch == 0) return;
//            }
//            else if (ands[i].contains("=")){
//                wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 2);
//                queue.add(wrappedExpression);
//            }
//            else if (ands[i].contains(">")){
//                wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 1);
//                queue.add(wrappedExpression);
//            }
//            else if (ands[i].contains("<")){
//                wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 1);
//                queue.add(wrappedExpression);
//            }
//            else if (ands[i].contains("like")){
//                wrappedExpression wrappedExpression = new wrappedExpression(ands[i], 0);
//                queue.add(wrappedExpression);
//            }
//        //根据String.contains() = 、> 、<、like来设置优先级，加入优先级队列。
//        }
//        //从PriorityQueue中取出去用AndParser解析;
//        AndParser andParser = new AndParser(map);
//        while (!queue.isEmpty()){
//            wrappedExpression remove = (wrappedExpression) queue.remove();
//            canMatch = andParser.doParse(remove.getSqlExpression(), canMatch);
//            System.out.println(remove.toString());
//        }
//        System.out.println(canMatch == 1);
////        System.out.println(Arrays.toString(ands));
//    }

    public static void main(String[] args) {
        String json = "{\"a\":1,\"b\":1.23,\"d\":\"hello\"}";
        String sql = "a = 2 and d like \"he%\" or a = 1";
        boolean b = query4(json, sql);
        System.out.println(b);
    }
}
