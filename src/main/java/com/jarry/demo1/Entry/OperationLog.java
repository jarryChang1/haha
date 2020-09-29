package com.jarry.demo1.Entry;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Entry
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-27 15:30
 */
@Data
public class OperationLog {
    private Long id;
    private Date createTime;

    private Long userId;
    private String userName;
    private Long userCompanyCode;

    private String operationDescribe;

    /**
     * 1 增加，2删除，3修改，4导出。//查询、登录等
     */
    private int operationType;
    private String method;
    private String params;
}
