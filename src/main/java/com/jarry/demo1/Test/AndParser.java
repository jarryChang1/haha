package com.jarry.demo1.Test;

import java.util.Map;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 14:07
 */
public class AndParser implements Parser {
    //先解析=号的查询，再解析其它的
    //如果遇到一个不匹配,则return null;
    protected Map<String, String> map;

    public AndParser(Map map) {
        this.map = map;
    }

    @Override
    public int doParse(String sqlString, int canMatch) {
        sqlString = sqlString.trim();
        String[] split = sqlString.split(" ");
        for (int i = 0; i < split.length; i += 3) {
            int tmpCanMatch = 0;
            String key = split[i];
            String op = split[i + 1];
            String value = split[i + 2];
            switch (op) {
                case ">":
                    if (Integer.valueOf(value) < Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                    break;
                case "<":
                    if (Integer.valueOf(value) > Integer.valueOf(map.get(key))) tmpCanMatch = 1;
                    break;
                case "=":
                    if (value.equals(map.get(key))) tmpCanMatch = 1;
                    break;
                case "like":
                    if (map.get(key).contains(value.replaceAll("%", ""))) tmpCanMatch = 1;
                    break;
            }
            canMatch &= tmpCanMatch;
        }
        return canMatch;
    }
}
