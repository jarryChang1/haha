package com.jarry.demo1.Entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-09 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
