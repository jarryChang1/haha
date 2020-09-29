package com.jarry.demo1.Entry;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Objects;

/**
 * @CreateTime: 2019-09-18 09:21
 */
@Data
public class UserBean implements Serializable {

    private static final long serialVersionUID = 4493638076637673025L;
    public String name;

    public int age;
    public int phone;
    public int email;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String uuid;

    //ssssss
    public UserBean(String s, int age, int phone, int email) {
        this.name = s;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public UserBean(String s) {
        this.name = s;
    }

    public UserBean(String s, int age) {
        this.name = s;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return age == userBean.age &&
                phone == userBean.phone &&
                email == userBean.email &&
                Objects.equals(name, userBean.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, phone, email);
    }

    public UserBean(String s, int age, int phone) {
        this.name = s;
        //sssssssssdcdsadsadsa
        this.age = age;
        this.phone = phone;
    }

    public UserBean() {

    }
}
