package com.jarry.demo1.constant;

import lombok.Data;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.constant
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-10 17:09
 */
@Data
public class Node {
    public Long key;

    public String value;

    Node(Long key,String value){
        this.key = key;

        this.value = value;
    }

    @Override
    public String toString(){
        return "Node{"+
                "key = "+ key +
                ", value" +value+'\''+
                '}';
    }
}
