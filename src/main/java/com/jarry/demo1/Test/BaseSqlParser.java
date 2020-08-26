package com.jarry.demo1.Test;

import java.util.List;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 11:16
 */
public  abstract class BaseSqlParser {

    protected String sqlString;

    protected List<String> sqlSplitedExpression;//用指定模式分隔的sql

    //如果有or，or前后的查询结果求并集，全部执行下去
    //如果没有or，则先过滤and前面的,
    protected abstract void splitSql();
    protected abstract boolean doparse();

    public BaseSqlParser(String sqlString){
        this.sqlString = sqlString;
    }




}
