package demo.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行，
 * scheduleAtFixedRate 如果任务执行时间较长，那么设置的定时时间就失效了,上一个任务执行完立即执行下一个任务
 * scheduleWithFixedDelay 下次任务的执行时间等于上次任务执行的时间加上定时时间
 */
@Slf4j(topic = "parade0393")
public class Scheduled {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
//        method(pool);
        pool.scheduleAtFixedRate(() -> {
            log.debug("running");
        }, 3, 1, TimeUnit.SECONDS);
        log.debug("start");
    }

    private static void method(ScheduledExecutorService pool) {
        pool.schedule(()->{
            log.debug("task1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },2, TimeUnit.SECONDS);
        pool.schedule(()->{
            log.debug("task2");
        },2, TimeUnit.SECONDS);
    }
}
