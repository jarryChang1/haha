package com.jarry.demo1.Config.JedisClusterConf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.Config.JedisClusterConf.Config.JedisClusterConf
 * @Author: Jarry.Chang
 * @CreateTime: 2020-04-02 14:16
 */

@Component
@ConfigurationProperties(prefix = "spring.redis.cache")
public class RedisProperties {

    private String clusterNodes;

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }


}
