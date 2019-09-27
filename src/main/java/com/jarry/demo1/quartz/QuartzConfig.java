package com.jarry.demo1.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.quartz
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-23 11:01
 */
@Configuration
public class QuartzConfig {
    public Scheduler scheduler() throws SchedulerException{

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = schedulerFactory.getScheduler();
        return  scheduler;
    }

}
