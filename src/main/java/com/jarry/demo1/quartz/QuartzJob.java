package com.jarry.demo1.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.quartz
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-23 11:38
 */
@Component
public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("干活开始！！！！！");
    }
}
