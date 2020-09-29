package com.jarry.demo1.java3y.ProxyMode.DeepCopy;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DeepCopy
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-10 16:56
 * <p>
 * 要copy的类 都必须实现Cloneable接口。
 */
public class Main {
    /**
     * 结论：
     * 1、浅拷贝对于基础类型的数据可以进行拷贝一份，但引用类型的对象没有拷贝
     * 2、深克隆就是要对 此对象下所有 引用对象都 重写clone方法。（Cloneable接口）
     * 调用super.clone()方法
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Subject subject = new Subject("语文");
        Student studentA = new Student();
        studentA.setAge(22);
        studentA.setName("jarry");
        studentA.setSubject(subject);
        System.out.println(studentA);
        Student studentB = (Student) studentA.clone();
        studentB.setName("lalala");
        studentB.setAge(23);

        Subject subjectB = studentB.getSubject();

        subjectB.setName("eleme");
        System.out.println(studentA);
        System.out.println(studentB);
    }
}
