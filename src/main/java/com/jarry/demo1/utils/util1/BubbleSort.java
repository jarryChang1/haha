package com.jarry.demo1.utils.util1;

import org.apache.catalina.mapper.Mapper;

import java.util.Map;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-24 16:40
 */
public class BubbleSort  {
    public static void main(String[] args) {
        int[] ints= {1,2,4,9,5,6,7,6};
        for (int i = 0; i < ints.length; i++) {

            for (int j = 0; j < ints.length-i-1; j++) {
                if(ints[j]>ints[j+1]) {
                    int temp = ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1]=temp;
                }
            }

        }
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
