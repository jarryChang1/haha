package com.jarry.demo1.Test;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Test
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-22 16:27
 */

/**
 * 配置和加载多个es集群实例,在application.yml配置文件中定义了两个集群default和logs
 * spring.elasticsearch.bboss.default
 * spring.elasticsearch.bboss.logs
 */


@Configuration
//@Profile("multi-datasource")
public class MultiESSTartConfigurer {

    @Bean(initMethod = "start")
    @ConfigurationProperties("spring.elasticsearch.bboss.default")
    public BBossESStarter bbossESStarterDefault() {
        return new BBossESStarter();
    }

    @Bean(initMethod = "start")
    @ConfigurationProperties("spring.elasticsearch.bboss.logs")
    public BBossESStarter bbossESStarterLogs() {
        return new BBossESStarter();
    }
}
