package com.jarry.demo1.Test;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 14:07
 */
public class AndParser implements Parser {
    //先解析=号的查询，再解析其它的
    @Override
    public boolean doParse(String sqlString) {
        return false;
    }
}
