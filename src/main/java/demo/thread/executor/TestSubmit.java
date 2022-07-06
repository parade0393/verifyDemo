package demo.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程执行带返回结果
 */
@Slf4j(topic = "parade")
public class TestSubmit {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.invokeAll(); //提交task中所有任务
//        service.invokeAll();//提交task中所有任务 带有超时时间
//        service.invokeAny();//提交task中所有任务,哪个任务先执行成功，返回此任务执行结果，其他任务取消
//        service.invokeAny();//同上一个，带超时时间
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);
                return "ok";
            }
        });
        try {
            String s = future.get();//打印线程执行的结果
            log.debug("执行结果{}",s);
            service.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
