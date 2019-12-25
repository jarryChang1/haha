package com.jarry.demo1.utils.util1;

import com.jarry.demo1.Entry.UserBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-24 12:21
 */
public class javareflect {
    /**
     * @Description 获得类的基本信息（类名、构造器、方法、属性）
     */

        public static void main(String[] args) {
            String userbean = "com.jarry.demo1.Entry.UserBean";
            main1();
            try {
                Class clazz = Class.forName(userbean);

                //获得类名
                String className = clazz.getName();  //
                String classSimpleName = clazz.getSimpleName();  //

                System.out.println(className);
                System.out.println(classSimpleName);

                //获得构造器
                //Constructor[] constructors = clazz.getConstructors();//只获得public修饰的构造器
                Constructor[] constructors = clazz.getDeclaredConstructors();//获得所有定义的构造器
                for(Constructor constructor:constructors){
                    System.out.println("构造器："+constructor);
                }
                //考虑重载问题
                Constructor c1 = clazz.getConstructor(null);//获得空构造
                Constructor c2 = clazz.getConstructor(String.class,int.class);//获得带参构造

                //获得方法
                //Method[] methods = clazz.getMethods();//只获得public修饰的方法
                Method[] methods = clazz.getDeclaredMethods();//获得全部方法
                for(Method method:methods){
                    System.out.println("方法："+method);
                }
                //考虑重载问题
                Method m1 = clazz.getMethod("getName",null);
                Method m2 = clazz.getMethod("setName",String.class);

                //获得属性
                //Field[] fields = clazz.getFields();//获得public修饰的属性
                Field[] fields = clazz.getDeclaredFields();//获得所有属性
                for(Field field:fields){
                    System.out.println("属性："+field);
                }
                Field f1 = clazz.getDeclaredField("name");//获得name属性


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public static void main1(){
            String userbean = "com.jarry.demo1.Entry.UserBean";
            try {
                Class<UserBean> beanClass = (Class<UserBean>) Class.forName(userbean);
                //通过反射调用无参构造
                UserBean u1 = beanClass.newInstance();
                System.out.println(u1);

                Constructor constructor = beanClass.getConstructor(String.class,int.class,int.class);
                UserBean u2 = (UserBean) constructor.newInstance("chang",23,13636);
                System.out.println(u2);

                Method method = beanClass.getDeclaredMethod("setUuid",String.class);
                method.invoke(u1,"UUidjarry");
                System.out.println(u1.getUuid());

                Field f = beanClass.getDeclaredField("age");
                f.setAccessible(true);
                f.set(u1,21);
                System.out.println(u1.getAge());
                System.out.println(f.get(u1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
