package com.jarry.demo1.Test;
import com.jarry.demo1.Entry.Article;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-21 15:50
 */
public class BBossESStarterTestCase {
//    @Autowired
//    private BBossESStarter bBossESStarter;
    @Autowired
    private BBossESStarter bbossESStarterDefault;

    @Autowired
    Article article;

    /**
     * ---------------------------------bbossESStarter必须要自动注入，test的局部配置文件就不起作用了。
     *
     */
    @Test
    public void testBbossESStarter() throws Exception{
//        bbossESStarterDefault = new BBossESStarter();
        System.out.println(bbossESStarterDefault);
        //验证环境，获取es状态
        boolean exist = bbossESStarterDefault.getRestClient().existIndiceType("es_article","article");
        System.out.println(exist);
        exist = bbossESStarterDefault.getRestClient("default").existIndiceType("es_article","article");
        System.out.println("logs twitter/tweet:"+exist);
        exist = bbossESStarterDefault.getRestClient("logs").existIndice("es_article");
        System.out.println(exist);
        exist = bbossESStarterDefault.getRestClient("logs").existIndice("tweet");
        System.out.println(exist);
    }
}
