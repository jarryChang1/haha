package com.jarry.demo1.utils.util1;

import com.jarry.demo1.Entry.UserBean;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-24 14:31
 */
public class reflect4Generic {
        /**
         * @Description 反射操作泛型
         */
        public void test01(Map<String,UserBean> map, List<UserBean> list){
            System.out.println("reflect4Generic.test01()");
        }

        public Map<Integer,UserBean> test02(){
            System.out.println("reflect4Generic.test02()");
            return null;
        }

        public static void main(String[] args) {
            try {
                //获得指定方法的参数泛型信息
                Method m1 = reflect4Generic.class.getMethod("test01",Map.class,List.class);
                Type[] types = m1.getGenericParameterTypes();//获得带泛型的参数类型
                for(Type type:types){
                    System.out.println(type);  //遍历参数类型
                    if(type instanceof ParameterizedType){
                        //获得真正的泛型信息
                        Type[] genericTypes = ((ParameterizedType)type).getActualTypeArguments();
                        for(Type genericType:genericTypes){
                            System.out.println("参数泛型类型："+genericType);
                        }
                    }
                }

                System.out.println("*******************************");

                //获得指定方法的返回值泛型信息
                Method m2 = reflect4Generic.class.getMethod("test02",null);
                Type returnTypes = m2.getGenericReturnType();//获得返回值的类型
                System.out.println(returnTypes);  //遍历参数类型
                if(returnTypes instanceof ParameterizedType){
                    //获得真正的泛型信息
                    Type[] genericTypes = ((ParameterizedType)returnTypes).getActualTypeArguments();
                    for(Type genericType:genericTypes){
                        System.out.println("返回值泛型类型："+genericType);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

}
