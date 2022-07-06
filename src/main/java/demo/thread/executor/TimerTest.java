package demo.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * timer的任务都由同一个线程来调度，因此所有任务都是串行执行的，同一时间只能有一个任务在执行，前面的任务延迟或者异常都会影响后面的任务
 */
@Slf4j(topic = "parade0393")
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                log.debug("task1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task2");
            }
        };
        log.debug("start");
        timer.schedule(timerTask,1000);
        timer.schedule(timerTask2,1000);
    }
}
