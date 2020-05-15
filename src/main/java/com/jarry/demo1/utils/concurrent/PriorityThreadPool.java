//package com.jarry.demo1.utils.concurrent;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Comparator;
//import java.util.concurrent.PriorityBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//
///**
// * @BelongsProject: demo1
// * @BelongsPackage: com.jarry.demo1.utils.concurrent
// * @Author: Jarry.Chang
// * @CreateTime: 2020-04-27 15:43
// */
//@Slf4j
//public class PriorityThreadPool {
//    public static void main(String[] args) throws InterruptedException {
//        /**仍需重写submit方法，使其利用priority*/
//        PriorityBlockingQueue<Runnable> runnables = new PriorityBlockingQueue<Runnable>(16);
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                2,
//                2,
//                Long.MAX_VALUE, /* timeout */
//                TimeUnit.NANOSECONDS,
//                runnables);
//
//        PriorityTask p1 = new PriorityTask(1234, "name1-1");
//        PriorityTask p2 = new PriorityTask(1500, "name4-2");
//        PriorityTask p3 = new PriorityTask(1590, "name5-3");
//        PriorityTask p4 = new PriorityTask(1490, "name3-4");
//        PriorityTask p5 = new PriorityTask(1290, "name2-5");
//        executor.execute(p4);
//        executor.execute(p1);
//        executor.execute(p2);
//        executor.execute(p3);
//        executor.execute(p5);
//        log.info("submit 5 Runnable");
//        Thread.sleep(30*1000);
////        ruleExecutor.shutdown();
//        log.info("done!");
//    }
//
//    @Data
//    static class PriorityTask implements Runnable{
//
//        private long priority;
//        private String name;
//
//        PriorityTask(long priority,String name){
//        this.priority = priority;
//        this.name = name;
//        }
//
//        @Override
//        public void run() {
//            System.out.println(this.name);
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //todo
//        }
//
////        public int compareTo(PriorityTask o1) {
////            if (this.priority < o1.getPriority()) return -1;
////            else if (this.priority > o1.getPriority())return 1;
////            else return 0;
////        }
//
//    }
//}
