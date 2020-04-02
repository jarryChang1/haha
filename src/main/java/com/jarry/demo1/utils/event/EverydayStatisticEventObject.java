package com.jarry.demo1.utils.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.event
 * @Author: Jarry.Chang
 * @CreateTime: 2020-04-01 18:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EverydayStatisticEventObject {

    private Integer id;

    private String os;

    private String proxy;

    private StatisticEventType statisticEventType;

}
