package com.jarry.demo1.Test;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 14:08
 */
public class OrParser implements Parser {
    //先解析or两边的，那个like，再解析 > 最后 =
    @Override
    public boolean doParse(String sqlString) {

        return false;
    }
}
