package com.jarry.demo1.Entry;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Entry
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-21 11:43
 */
@Data
public class Message implements Serializable {
    private String meassage;
}
