package com.jarry.demo1.java3y.ProxyMode.DeepCopy;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DeepCopy
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 16:55
 */
@Data
public class Student implements Cloneable,Serializable {

    //引用类型
    private Subject subject;
    //基础数据类型
    private String name;
    private int age;

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        clone.subject = (Subject) (subject.clone());
        return clone;
    }

    @Override
    public String toString() {
        return "[Student: " + this.hashCode()
                + ",subject:" + subject
                + ",name:" + name
                + ",age:" + age + "]";
    }
}
