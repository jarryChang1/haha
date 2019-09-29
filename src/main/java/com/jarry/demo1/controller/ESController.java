package com.jarry.demo1.controller;

import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.dao.imp.inner.ArticleSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-29 12:03
 */
@Slf4j
@RestController
public class ESController {
    @Autowired
    ArticleSearchRepository articleSearchRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建文档索引
    @Test
    public void creationIndex(){ elasticsearchTemplate.createIndex(Article.class);}
    //设置映射关系
    @Test
    public void creationMapping(){elasticsearchTemplate.putMapping(Article.class);}
    //删除索引
    @Test
    public void deleteIndex(){elasticsearchTemplate.deleteIndex(Article.class);}

    @GetMapping("/lala")
    public void searchByPage(){
        Page<Article> articlePage = null;
         articlePage = articleSearchRepository.findAll(PageRequest.of(0,2));
         if(articlePage != null){
        for (Article a:articlePage
             ) {
            System.out.println(a);
        }
        }

    }
    @GetMapping("/lalala")
    public void test2(){
        //查询所有的里面指定排序的字段，并可以继续调用排序方式（升ascending降descending）,默认降序
        Iterable<Article> items = articleSearchRepository.findAll(Sort.by("id").descending());
//        items.forEach(System.out::println);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("abstracts","easy"));
        Page<Article> search = articleSearchRepository.search(nativeSearchQueryBuilder.build());
        search.stream().forEach(System.out::println);
    }
    @Test
    public void jhSearch(){
        //创建查询条件构造器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
//        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        //添加聚合 (此聚合类型为terms，聚合名称是：categorys,聚合字段是category)
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("categorys").field("category"));
        //查询（结果修改为聚合类分页结果）
        AggregatedPage<Article> search = (AggregatedPage<Article> )articleSearchRepository.search(nativeSearchQueryBuilder.build());

        //结果中找到对应的聚合（根据聚合名称）
        StringTerms category = (StringTerms)search.getAggregation("categorys");
        //获取查询到的桶
        List<StringTerms.Bucket> buckets = category.getBuckets();

        for (StringTerms.Bucket s:buckets
                ) {
            //获取桶中的key（就是字段下的名称）
            String keyAsString = s.getKeyAsString();
            System.out.println( keyAsString);
            //获取桶中的数量（即为查询到的文档数量）
            long docCount = s.getDocCount();
            System.out.println(docCount);
        }

    }



    @RequestMapping("/add")
    public void testSaveArticleIndex(){
        Article article = new Article();
        article.setId(1L);
        article.setTitle("springboot integreate elasticsearch is very easy");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setContent("elasticsearch based on lucene");
        article.setPostTime(new Date());
        articleSearchRepository.save(article);
        articleSearchRepository.saveAll(Arrays.asList(article,new Article()));
        System.out.println("ok");

    }

    @GetMapping("/query")
    public void testSearch(){
        String queryString = "springboot";
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> articles = articleSearchRepository.search(builder);
        for (Article article :articles) {
            System.out.println(article);
        }
    }

}
