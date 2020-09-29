package com.jarry.demo1.java3y.ProxyMode.LeakBuket;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.LeakBuket
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-07 09:57
 */
public class singleBena {

    private singleBena singleBena = null;

    private singleBena() {

    }

    public singleBena constract() {
        if (singleBena == null) {
            singleBena = new singleBena();
        }
        return singleBena;
    }
}
