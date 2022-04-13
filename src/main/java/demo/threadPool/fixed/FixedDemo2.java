package demo.threadPool.fixed;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedDemo2 {
    public static void main(String[] args) {
        int length = 112;
        final int[] count = {0};
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(length);
        executorService.execute(()->{
            for (int i = 0; i < length; i++) {
                Observable.just(0).delay(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName()+"count="+atomicInteger.get());
                        countDownLatch.countDown();
                        atomicInteger.incrementAndGet();
                    }
                });
            }
        });

        Observable.just(0).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                countDownLatch.await();
                executorService.shutdown();
                System.out.println("数量是："+atomicInteger.get());
                System.out.println("执行完毕");
            }
        });
        while (true){

        }
    }
}
