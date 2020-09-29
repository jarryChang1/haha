package com.jarry.demo1.Test;

import java.util.Map;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 14:08
 */
public class OrParser implements Parser {


    protected Map<String, String> map;

    public OrParser(Map map) {
        this.map = map;
    }

    //先解析or两边的，先like，再解析 > 最后 =
    //如果or两边的匹配，则返回true;如果所有的or两边都不匹配则返回false;
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
            canMatch |= tmpCanMatch;
        }
        return canMatch;
    }
}
