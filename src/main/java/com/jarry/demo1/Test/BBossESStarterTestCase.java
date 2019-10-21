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
    @Autowired
    private BBossESStarter bBossESStarter;
    @Autowired
    Article article;

    @Test
    public void testBbossESStarter() throws Exception{
        bBossESStarter = new BBossESStarter();
        System.out.println(bBossESStarter);
        //验证环境，获取es状态
        boolean exist = bBossESStarter.getRestClient().existIndiceType("es_article","article");
        System.out.println(exist);
        exist = bBossESStarter.getRestClient().existIndice("es_article");
        System.out.println(exist);
        exist = bBossESStarter.getRestClient().existIndice("tweet");
        System.out.println(exist);
    }
}
