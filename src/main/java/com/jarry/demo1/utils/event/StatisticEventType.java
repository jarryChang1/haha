package com.jarry.demo1.utils.event;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.event
 * @Author: Jarry.Chang
 * @CreateTime: 2020-04-01 18:10
 */
public enum StatisticEventType {

    //注册数统计
    REGISTER_COUNTER,
    //活跃数统计
    ACTIVE_COUNTER,
    //裂变数统计
    FISSION_COUNTER,
    //播放数统计
    PLAYED_COUNTER,
    //广告点击数统计
    ADCLICK_COUNTER;

    private StatisticEventType() {
    }
}
