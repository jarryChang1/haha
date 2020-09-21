package com.jarry.demo1.java3y.ProxyMode.DeepCopy;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DeepCopy
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 16:54
 */
@Data
public class Subject implements Cloneable,Serializable {

    private String name;

    public Subject(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return  super.clone();
    }

    @Override
    public String toString() {
        return "[Subject: " + this.hashCode() + ",name:" + name + "]";
    }
}
