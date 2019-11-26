package com.jarry.demo1.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.util.Date;
import java.util.Timer;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.quartz
 * @Author: Jarry.Chang
 * @CreateTime: 2019-09-23 11:07
 */
@Component
public class Quartz {
    private static String JOBGROUP = "group1";
    @Resource
    private Scheduler scheduler;

    @Autowired
    private QuartzJob quartzJob;

    public static CronTrigger addQuartz() throws SchedulerException{
        Long taskId = 1L;
        String CronValue = "1-2 * * * * ? ";
        QuartzConfig quartzConfig = new QuartzConfig();
        Scheduler scheduler = quartzConfig.scheduler();
        CronTrigger trigger = null;
            TriggerKey triggerKey = TriggerKey.triggerKey(taskId.toString(),JOBGROUP);
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //存在任务
            // Trigger已存在，那么更新相应的定时设置
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CronValue);
            trigger = trigger.getTriggerBuilder()//没有绑定任务，会出现空指针
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.rescheduleJob(triggerKey,trigger);
        return trigger;
    }
    public static CronTrigger addOrUpdateQuartz() throws SchedulerException {
        Long taskId = (long) 2;

        String CronValue = "*/1 * * * * ? *";

        QuartzConfig quartzConfig = new QuartzConfig();

        Scheduler scheduler = quartzConfig.scheduler();
        CronTrigger trigger = null;
        try {
            //获取触发器标识
            TriggerKey triggerKey = TriggerKey.triggerKey(taskId.toString(), JOBGROUP);
            //获取触发器trigger
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {//不存在任务

                //创建任务
                JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                        .withIdentity(taskId.toString(), JOBGROUP)
                        .build();
                jobDetail.getJobDataMap().put("taskID", taskId);
                //表达式调度构建器
                //这里的时间也可以通过页面传送过来。
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CronValue)
                        .withMisfireHandlingInstructionDoNothing();
                //按新的cronExpression表达式构建一个新的trigger
                //手动的优先级为5 自动的优先级按照高中低为3，2，1
                int priority = 3;
//
                /**
                 * 设置触发器
                 */
                trigger = newTrigger()
                        .withIdentity(taskId.toString(),JOBGROUP)
                        .withPriority(priority)
                        .withSchedule(scheduleBuilder)
                        .startNow()
                        .endAt(new Date(2020,9,30,14,30,60))
                        .build();

//                TriggerBuilder<CronTrigger> builder = newTrigger()
//                        .withIdentity(taskId.toString(), JOBGROUP)
//                        .withPriority(priority)
//                        .withSchedule(scheduleBuilder);
//                builder.startAt(new Date());
//                builder.endAt(new Date(2020,9,30,14,30,60));
////                builder.startNow();
//                trigger = builder.build();
//                Trigger trigger1 = newTrigger()
//                        .usingJobData();
                /**
                 * 设置调度器
                 */
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
            } else {
                //存在任务
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CronValue);
                trigger = trigger.getTriggerBuilder()
                        .withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {

        }
        return trigger;

    }
}
