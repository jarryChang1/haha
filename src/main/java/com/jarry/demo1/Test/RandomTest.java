package com.jarry.demo1.Test;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2020-05-18 11:02
 */
public class RandomTest {

    public static Random localRandom = new Random();

    @Test
    public int test1(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        int i = localRandom.nextInt(list.size());

        int j = (int)list.get(i);
//        System.out.println(j);
        return j;
    }


    @Test
    public void main() {
        int count = 0;
        for (int i = 0;i < 100;i++){
            int j = this.test1();
            if (j == 2) count++;
        }
        System.out.println(count);
    }
}
