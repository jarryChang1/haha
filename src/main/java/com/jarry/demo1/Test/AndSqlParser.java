package com.jarry.demo1.Test;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 12:16
 */
public class AndSqlParser extends BaseSqlParser {

    public AndSqlParser(String sqlString) {
        super(sqlString);
    }

    @Override
    protected void splitSql() {
        String[] split = sqlString.split(" ");
    }

    @Override
    protected boolean doparse() {
        return false;
    }
}
