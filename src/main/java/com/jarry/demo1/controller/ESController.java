package com.jarry.demo1.controller;

import com.jarry.demo1.Entry.Article;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.DB2ESImportBuilder;
import org.frameworkset.elasticsearch.client.DataStream;
import org.frameworkset.elasticsearch.client.ResultUtil;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.MapRestResponse;
import org.frameworkset.elasticsearch.entity.MapSearchHit;
import org.frameworkset.elasticsearch.entity.MapSearchHits;
import org.frameworkset.elasticsearch.scroll.HandlerInfo;
import org.frameworkset.elasticsearch.scroll.ScrollHandler;
import org.frameworkset.elasticsearch.serial.ESTypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @CreateTime: 2019-09-29 12:03
 */
@Slf4j
@RestController
public class ESController {
//    @Autowired
//    ArticleSearchRepository articleSearchRepository;
    @Autowired
    private BBossESStarter bbossESStarterDefault;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
//    @Resource
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    //创建文档索引
//    @Test
//    public void creationIndex(){ elasticsearchTemplate.createIndex(Article.class);}
//    //设置映射关系
//    @Test
//    public void creationMapping(){elasticsearchTemplate.putMapping(Article.class);}
//    //删除索引
//    @Test
//    public void deleteIndex(){elasticsearchTemplate.deleteIndex(Article.class);}
//
//    @GetMapping("/lala")
//    public void searchByPage(){
//        Page<Article> articlePage = null;
//         articlePage = articleSearchRepository.findAll(PageRequest.of(0,2));
//         if(articlePage != null){
//        for (Article a:articlePage
//             ) {
//            System.out.println(a);
//        }
//        }
//
//    }
//    @GetMapping("/lalala")
//    public void test2(){
//        //查询所有的里面指定排序的字段，并可以继续调用排序方式（升ascending降descending）,默认降序
//        Iterable<Article> items = articleSearchRepository.findAll(Sort.by("id").descending());
////        items.forEach(System.out::println);
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("abstracts","easy")); // nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery("easy").defaultField("abstracts"));
//        Page<Article> search = articleSearchRepository.search(nativeSearchQueryBuilder.build());
//        search.stream().forEach(System.out::println);
//    }
//    @Test
//    public void jhSearch(){
//        //创建查询条件构造器
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        // 不查询任何结果
////        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
//        //添加聚合 (此聚合类型为terms，聚合名称是：categorys,聚合字段是category)
//        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("categorys").field("category"));
//        //查询（结果修改为聚合类分页结果）
//        AggregatedPage<Article> search = (AggregatedPage<Article> )articleSearchRepository.search(nativeSearchQueryBuilder.build());
//
//        //结果中找到对应的聚合（根据聚合名称）
//        StringTerms category = (StringTerms)search.getAggregation("categorys");
//        //获取查询到的桶
//        List<StringTerms.Bucket> buckets = category.getBuckets();
//
//        for (StringTerms.Bucket s:buckets
//                ) {
//            //获取桶中的key（就是字段下的名称）
//            String keyAsString = s.getKeyAsString();
//            System.out.println( keyAsString);
//            //获取桶中的数量（即为查询到的文档数量）
//            long docCount = s.getDocCount();
//            System.out.println(docCount);
//        }
//
//    }
//
//
//
//    @RequestMapping("/add")
//    public void testSaveArticleIndex(){
//        Article article = new Article();
//        article.setId(1L);
//        article.setTitle("springboot integreate elasticsearch is very easy");
//        article.setAbstracts("springboot integreate elasticsearch is very easy");
//        article.setContent("elasticsearch based on lucene");
//        article.setPostTime(new Date());
//        articleSearchRepository.save(article);
//        articleSearchRepository.saveAll(Arrays.asList(article,new Article()));
////        System.out.println("ok");
//
//    }
//
//    @GetMapping("/query")
//    public void testSearch(){
//        String queryString = "springboot";
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
//        Iterable<Article> articles = articleSearchRepository.search(builder);
//        for (Article article :articles) {
//            System.out.println(article);
//        }
//    }
    @GetMapping("esScrolltest")
    public void esScrolltest(){
        ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("logs","esmapper/scroll.xml");
        //scroll分页检索
        long startTime=System.currentTimeMillis();
        Map params = new HashMap();
        params.put("size", 10000);//每页10000条记录
        //scroll上下文有效期1分钟,每次scroll检索的结果都会合并到总得结果集中；数据量大时存在oom内存溢出风险，大数据量时可以采用handler函数来处理每次scroll检索的结果(后面介绍)
        ESDatas<Map> response = clientUtil.scroll("1089_13/_search","scrollQuery","1m",params,Map.class);
        List<Map> datas = response.getDatas();
        long realTotalSize = datas.size();
        long totalSize = response.getTotalSize();
        long endTime=System.currentTimeMillis();
        System.out.println("totalSize:"+totalSize);
        System.out.println("realTotalSize:"+realTotalSize);
        System.out.println("countAll:"+clientUtil.countAll("1089_13"));
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

    @GetMapping("esScroll")
    public void esScroll(){
        ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("logs","esmapper/scroll.xml");
        //scroll分页检索
        System.out.println(clientUtil);
        long startTime=System.currentTimeMillis();
        Map params = new HashMap();
        params.put("taskId", 489);//每页10000条记录
        //scroll上下文有效期1分钟,每次scroll检索的结果都会合并到总得结果集中；数据量大时存在oom内存溢出风险，大数据量时可以采用handler函数来处理每次scroll检索的结果(后面介绍)
        MapRestResponse response = clientUtil.search("1089_13/_search","scrollQuery",params);
        MapSearchHits list = response.getSearchHits();
        Long l = ResultUtil.longValue(list.getTotal(),0L);
        System.out.println(l);
        long time = response.getTook();
//        System.out.println(datas.subList(0,111));
        long realTotalSize = response.getCount();
        long totalSize = response.getCount();
        System.out.println("totalSize:"+totalSize);
        System.out.println("realTotalSize:"+realTotalSize);
        System.out.println("countAll:"+clientUtil.countAll("1089_13"));
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+time+"ms");
//        long startTime=System.currentTimeMillis();
//        Map params = new HashMap();
//        params.put("size", 5000);//每页5000条记录
//        //采用自定义handler函数处理每个scroll的结果集后，response中只会包含总记录数，不会包含记录集合
//        //scroll上下文有效期1分钟；大数据量时可以采用handler函数来处理每次scroll检索的结果，规避数据量大时存在的oom内存溢出风险
//        ESDatas<Map> response = clientUtil.scroll("1089_13/_search", "scrollQuery", "1m", params, Map.class, new ScrollHandler<Map>(){
//            public void handle(ESDatas<Map> response, HandlerInfo handlerInfo) {//自己处理每次scroll的结果
//                List<Map> datas = response.getDatas();
//                long totalSize = response.getTotalSize();
//                System.out.println("totalSize:"+totalSize+",datas.size:"+datas.size());
//            }
//        });
//        long endTime=System.currentTimeMillis();
//        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
//        System.out.println("response realzie:"+response.getTotalSize());
    }

    @GetMapping("/esQuery")
    public void esQuery(){
        System.out.println(bbossESStarterDefault);
        //验证环境，获取es状态
        boolean exist = bbossESStarterDefault.getRestClient().existIndiceType("1089_13","1089_13");
        System.out.println(exist);
        exist = ElasticSearchHelper.getRestClientUtil("logs").existIndiceType("1089_13","1089_13");//指定集群名称logs

//        exist = bbossESStarterDefault.getRestClient("default").existIndiceType("es_article","article");
        System.out.println("logs twitter/tweet:"+exist);
        exist = ElasticSearchHelper.getRestClientUtil().existIndice("1089_29");//指定集群名称default;
        System.out.println(exist);
        exist = ElasticSearchHelper.getRestClientUtil().existIndice("demo1");
        System.out.println(exist);
    }
    @GetMapping("/DB2ES")
    public void DataBase2ES(String index,String type,String tablename){//（不同需求）可以多传入索引字段，加快效率
        DB2ESImportBuilder importBuilder = DB2ESImportBuilder.newInstance();
        ClientInterface clientInterface = bbossESStarterDefault.getRestClient();

        if (!clientInterface.existIndice(index)) {
            log.info("--------------------------------index not exist:{}",index);
            clientInterface.createIndiceMapping(index, null);
        }
        Long count = clientInterface.countAll(index);
        //数据源相关配置，可选项，可以在外部启动数据源
        importBuilder.setDbName("mybase")
                .setDbDriver("com.mysql.jdbc.Driver") //数据库驱动程序，必须导入相关数据库的驱动jar包
                .setDbUrl(url+"&useCursorFetch=true") //通过useCursorFetch=true启用mysql的游标fetch机制，否则会有严重的性能隐患，useCursorFetch必须和jdbcFetchSize参数配合使用，否则不会生效
                .setDbUser(username)
                .setDbPassword(password)
                .setValidateSQL("select 1")
                .setUsePool(false);//是否使用连接池u
        //指定导入数据的sql语句，必填项，可以设置自己的提取逻辑
        importBuilder.setSql("select * from "+tablename);
        /**
         * es相关配置
         */
        importBuilder
                .setIndex(index) //必填项
                .setIndexType(type) //必填项
                .setRefreshOption("refresh")//可选项，null表示不实时刷新，importBuilder.setRefreshOption("refresh");表示实时刷新
                .setUseJavaName(true) //可选项,将数据库字段名称转换为java驼峰规范的名称，例如:doc_id -> docId
                .setBatchSize(5000)  //可选项,批量导入es的记录数，默认为-1，逐条处理，> 0时批量处理
                .setJdbcFetchSize(10000);//设置数据库的查询fetchsize，同时在mysql url上设置useCursorFetch=true启用mysql的游标fetch机制，否则会有严重的性能隐患，jdbcFetchSize必须和useCursorFetch参数配合使用，否则不会生效
        /**
         * 执行数据库表数据导入es操作
         */
        long start = System.currentTimeMillis();
        DataStream dataStream = importBuilder.builder();
        dataStream.execute();
        long end = System.currentTimeMillis();
        log.info("导入用时{}秒,导入文档数量：{}",(end-start)/1000,clientInterface.countAll(index)-count);

    }

    @PostMapping("aa")
    public String aa(@RequestBody String message) {
        log.info("-----------+++++++++++++++++++++++++++++++"+message);
        return message;
    }
}
