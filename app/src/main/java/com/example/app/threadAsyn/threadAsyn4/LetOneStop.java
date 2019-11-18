package com.example.app.ThreadAsyn.threadAsyn4;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.app.ThreadAsyn.threadAsyn4
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-18 12:02
 */
public class LetOneStop implements Animal.Callback {

    Animal an;

    public  LetOneStop(Animal an){
        this.an = an;
    }

    @Override
    public void win() {
        an.stop();
    }
}
