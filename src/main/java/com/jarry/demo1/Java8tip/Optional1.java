package com.jarry.demo1.Java8tip;

import com.jarry.demo1.Entry.UserBean;
import com.sun.org.apache.bcel.internal.classfile.Unknown;

import java.util.Optional;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Java8tip
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-24 15:29
 */
public class Optional1 {

    UserBean userBean = new UserBean();
    Optional<UserBean> optionalBean = Optional.ofNullable(userBean);

    private String main(){

    optionalBean.ifPresent(System.out::println);
    if(optionalBean != null){ System.out.println(optionalBean); }

        /**
         * -----------------------------------------------------
         */
       // return optionalBean.orElse(null);
           /*
        if(optionalBean.isPresent()){
            return optionalBean.get();
        }
        else return null;*/
          return optionalBean.map(p ->p.getName()).map(name -> name.toUpperCase()).orElse(null);
    }
}
