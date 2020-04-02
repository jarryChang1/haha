package com.jarry.demo1.Entry;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseRowModel implements Serializable {

    private static Article article;
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

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        System.out.println(this);
        Article.article = this;
    }
//    public static void main(String[] args) throws InterruptedException {
//        article = new Article();
//        System.out.println(article);
//        // 对象第一次拯救自己
//        article = null;
//        System.out.println(article);
//        System.gc();
//        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
//        Thread.sleep(500);
//        if (article != null) {
//            article.toString();
//        } else {
//            System.out.println("no, i am dead : (");
//        }
//
//        // 下面这段代码与上面的完全相同,但是这一次自救却失败了
//        // 一个对象的finalize方法只会被调用一次
//        article = null;
//        System.gc();
//        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
//        Thread.sleep(500);
//        if (article != null) {
//            article.toString();
//        } else {
//            System.out.println("no, i am dead : (");
//        }
//    }

}
