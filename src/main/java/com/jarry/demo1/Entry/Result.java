package com.jarry.demo1.Entry;

import lombok.Data;

/**
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-09 17:57
 */
@Data
public class Result<T> {

    /**
     * 状态码
     *
     */
    String StateCode;
    /**
     * 提示信息
     */
    String Desc;
    /**
     * 具体内容
     */
    T    Data;
}
