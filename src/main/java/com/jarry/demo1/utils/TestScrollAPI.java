package com.jarry.demo1.utils;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.scroll.HandlerInfo;
import org.frameworkset.elasticsearch.scroll.ScrollHandler;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-30 11:02
 */
public class TestScrollAPI {

//配置的ESclient必须要自动注入

//    @Test
//    public void testSimleScrollAPI(){
//        ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/scroll.xml");
//        //scroll分页检索
//
//        Map params = new HashMap();
//        params.put("size", 10000);//每页10000条记录
//        //scroll上下文有效期1分钟,每次scroll检索的结果都会合并到总得结果集中；数据量大时存在oom内存溢出风险，大数据量时可以采用handler函数来处理每次scroll检索的结果(后面介绍)
//        ESDatas<Map> response = clientUtil.scroll("1089_13/_search","scrollQuery","1m",params,Map.class);
//        List<Map> datas = response.getDatas();
//        long realTotalSize = datas.size();
//        long totalSize = response.getTotalSize();
//        System.out.println("totalSize:"+totalSize);
//        System.out.println("realTotalSize:"+realTotalSize);
//        System.out.println("countAll:"+clientUtil.countAll("1089_13"));
//    }

    @Test
    public void testSimleScrollAPIHandler() {
        ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/scroll.xml");
        //scroll分页检索

        Map params = new HashMap();
        params.put("size", 5000);//每页5000条记录
        //采用自定义handler函数处理每个scroll的结果集后，response中只会包含总记录数，不会包含记录集合
        //scroll上下文有效期1分钟；大数据量时可以采用handler函数来处理每次scroll检索的结果，规避数据量大时存在的oom内存溢出风险
        ESDatas<Map> response = clientUtil.scroll("demo/_search", "scrollQuery", "1m", params, Map.class, new ScrollHandler<Map>() {
            public void handle(ESDatas<Map> response, HandlerInfo handlerInfo) throws Exception {//自己处理每次scroll的结果
                List<Map> datas = response.getDatas();
                long totalSize = response.getTotalSize();
                System.out.println("totalSize:" + totalSize + ",datas.size:" + datas.size());
            }
        });

        System.out.println("response realzie:" + response.getTotalSize());

    }

}
