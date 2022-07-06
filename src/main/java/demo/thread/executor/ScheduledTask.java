package demo.thread.executor;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 每周四18:00:00定时执行任务
 */
public class ScheduledTask {
    public static void main(String[] args) {
        //线程安全和时间运算方便
        LocalDateTime now = LocalDateTime.now();//当前时间

//        System.out.println(format);
        //获取周四时间
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.TUESDAY);

        //如果当前时间大于本周周四，必须找到下周周四
        if (now.compareTo(time) > 0){
            time = time.plusWeeks(1);
        }



        //initDelay代表当前时间和周四时间差
        //period一周时间时间间隔


        long initDelay  = Duration.between(now, time).toMillis();
        long period = 1000*60*60*24*7;

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        pool.scheduleAtFixedRate(()->{},initDelay,period, TimeUnit.SECONDS);
    }
}
