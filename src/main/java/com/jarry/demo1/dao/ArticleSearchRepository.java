//package com.jarry.demo1.dao.imp.inner;
//
//import com.jarry.demo1.Entry.Article;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
////import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
///**
// * @CreateTime: 2019-09-29 11:59
// */
//
//public interface ArticleSearchRepository extends ElasticsearchRepository<Article,Long> {
//
//    /**
//     * 根据标题查询,不用实现，根据Es命名规则命名，es会帮助实现
//     */
//    public List<Article> findByTitle(String title);
//
//    /**
//     * 根据时间区间查询(直接传对象进去)
//     */
//    public List<Article> findByPostTimeBetween(Double d1,Double d2);
//}
