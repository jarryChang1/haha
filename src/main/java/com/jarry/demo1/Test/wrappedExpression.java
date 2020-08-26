package com.jarry.demo1.Test;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-08-26 16:28
 */
public class wrappedExpression implements Comparable<wrappedExpression> {

    private String sqlExpression;

    private int priority;

    public String getSqlExpression() {
        return sqlExpression;
    }

    public void setSqlExpression(String sqlExpression) {
        this.sqlExpression = sqlExpression;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public wrappedExpression(String sqlExpression, int priority){
        this.sqlExpression = sqlExpression;
        this.priority = priority;
    }
    @Override
    public int compareTo(wrappedExpression o) {
        return Integer.compare(o.priority, this.priority);
    }

    @Override
    public String toString() {
        return "wrappedExpression{" +
                "sqlExpression='" + sqlExpression + '\'' +
                ", priority=" + priority +
                '}';
    }
}
