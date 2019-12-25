package com.jarry.demo1.utils.util1;


import org.apache.ibatis.javassist.*;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-24 11:43
 */
public class byteCode {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.jarry.demo1.Entry.Stu");
        //创建属性并添加
        CtField field = CtField.make("private int age;",ctClass);
        CtField field2 = CtField.make("private String name; ",ctClass);
        ctClass.addField(field);
        ctClass.addField(field2);

        //创建方法并添加
        CtMethod method1 = CtMethod.make("public int getAge(){return this.age;}",ctClass);
        CtMethod method2 = CtMethod.make("public void setAge(int age){this.age=age;}",ctClass);
        ctClass.addMethod(method1);
        ctClass.addMethod(method2);

        //创建构造器并添加
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType,pool.get("java.lang.String")},ctClass);
        ctConstructor.setBody("{this.age = age;this.name = name;}");
        ctClass.addConstructor(ctConstructor);
        //写到本地，生成.class文件
        ctClass.writeFile("D:/myjava");
        //通过反编译软件XJad可以获得Stu.class文件的java文件
    }
}
