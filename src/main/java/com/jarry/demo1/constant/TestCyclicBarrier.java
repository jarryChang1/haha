package com.jarry.demo1.constant;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.constant
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-30 16:31
 */
public class TestCyclicBarrier {
    class Worker implements Runnable{
        private  volatile int  i =0,j = 0;
        CyclicBarrier cyclicBarrier;
        public Worker(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        AtomicInteger l = new AtomicInteger(12);//1100
        @Override
        public void run() {
            while( j < 100) {
                try {
                    cyclicBarrier.await(); // 等待其它线程
                    int f = l.intValue();
                if(l.compareAndSet(f, f|16)) i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                j++;
            }
            System.out.println(i);
            System.out.println(l.intValue());
        }
    }
    public void doTest() throws InterruptedException{
        final int N = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Thread(new Worker(cyclicBarrier)).start();
        }

    }

//    public static void main(String[] args) throws InterruptedException {
//        TestCyclicBarrier testCyclicBarrier = new TestCyclicBarrier();
//        testCyclicBarrier.doTest();
//    }
}
