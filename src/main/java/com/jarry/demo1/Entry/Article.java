package com.jarry.demo1.Entry;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import java.io.Serializable;
import java.util.Date;

/**
 * 创建实体
 * 这个实体能否与elasticsearch中的对象关联？
 * 文档：document，每一条记录就是一个文档
 * indexName :文档的索引的名字，唯一标识
 * type : 文档的类型 doc ppt xls story product
 * shards :数据库分片个数  默认将你的数据存6份，5个主份，1个备份。
 * @CreateTime: 2019-09-29 11:51
 */
//indexName  索引库的名称，indexStoreType 索引文件储存类型，
@Document(indexName = "es_article",type = "article",indexStoreType = "fs",shards = 5,replicas = 1)
@Data
public class Article extends BaseRowModel implements Serializable {

    @Id
    @ExcelProperty(value = "ID",index = 0)
    private Long id;
    @ExcelProperty(value = "标题",index = 1)
    private String title;//标题
    @ExcelProperty(value = "摘要",index = 2)
    private String abstracts;//摘要
    @ExcelProperty(value = "内容",index = 3)
    private String content;//内容
    @ExcelProperty(value = "发表时间",index = 4)
    private Date postTime;//发表时间

}
