//package com.jarry.demo1.Test;
//
///**
// * @BelongsProject: demo1
// * @BelongsPackage: com.jarry.demo1.Test
// * @Author: Jarry.Chang
// * @CreateTime: 2019-11-04 12:00
// */
//import org.jasypt.util.text.BasicTextEncryptor;
//
//import org.junit.jupiter.api.Test;
//
//public class UtilTests {
//    @Test
//    public void jasyptTest() {
//        BasicTextEncryptor encryptor = new BasicTextEncryptor();
//        // application.properties, jasypt.encryptor.password‘’
//        //该abc表示 jasypt.encryptor.password解密的密钥
//        encryptor.setPassword("abc");
//        // encrypt root 加密
//        System.out.println(encryptor.encrypt("tms"));
//        System.out.println(encryptor.encrypt("Tms123qwe!"));
//        System.out.println(encryptor.encrypt("root"));
//
//        // decrypt, the result is root，解密
////        System.out.println(encryptor.decrypt("t7qe1Ey87tOJya674+g0Gw=="));
////        System.out.println(encryptor.decrypt("h/auZHgLIDjzaKivZYAejx7g13UB1gbq"));
//        System.out.println(encryptor.decrypt("9Obo/jq9EqmTE0QZaJFYrw=="));
//    }
//}