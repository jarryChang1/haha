package com.jarry.demo1.Test;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 16:13
 */
public class Context {

    private Parser parser;

    public Context(Parser parser) {
        this.parser = parser;
    }

    public int excuteStrategy(String sqlString, int canMatch) {
        return parser.doParse(sqlString, canMatch);
    }
}
