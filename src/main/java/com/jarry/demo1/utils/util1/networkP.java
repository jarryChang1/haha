package com.jarry.demo1.utils.util1;

import com.jarry.demo1.Entry.UserBean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-23 14:31
 */
public class networkP {
    InetAddress address;

    {
        try {
            address = InetAddress.getLocalHost();
            address = InetAddress.getByName("www.baidu.com");
            address = InetAddress.getByName("192.168.1.191");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
//final类不可被继承，final方法不可变，（不可被重写）
    //final变量为常量，相当于const、@#define。不可变
    //final修饰引用类型时，引用后地址(指针)不可变


    URL url;

    {
        try {
            url = new URL("www.baidu.com:80/index.jsp");
            InputStream inputStream = url.openStream();
            boolean markSupported = inputStream.markSupported();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Long l = 1572922370974L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date(l)));
        System.out.println(l);

        //测试序列化与反序列化
        UserBean userBean = new UserBean("jarry",22,22);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:/file01.txt")));
        oos.writeObject(userBean);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:/file01.txt")));
        UserBean userBean1 = (UserBean) ois.readObject();

        System.out.println(userBean1.toString());
    }

}
