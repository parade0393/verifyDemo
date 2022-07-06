package demo.thread.executor;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * SynchronousQueue
 * 特点是没有容量，等有线程取了，其他线程才能放近去数据
 * Executors.newCachedThreadPool()使用了这个队列，任务量没有上限‘适用于任务密集，且执行之间比较短
 */
@Slf4j(topic = "parade0393")
public class SyncQueue {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();
        new Thread(()->{
            try {
                log.debug("putting{}",1);
                integers.put(1);
                log.debug("putted{}",1);

                log.debug("putting{}",2);
                integers.put(2);
                log.debug("putted{}",2);
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("taking {}",1);
                integers.take();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("taking {}",2);
                integers.take();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t3").start();
    }
}
