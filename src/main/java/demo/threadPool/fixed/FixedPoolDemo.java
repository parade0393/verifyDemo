package demo.threadPool.fixed;

import demo.util.DateUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPoolDemo {
    public static void main(String[] args) {
        int length = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(length);

        for (int i = 0; i < length; i++) {
            final int index = i;
            executorService.execute(() ->{
                //这里本来就不是在主线程里执行的
                System.out.println(DateUtil.INSTANCE.now()+" "+Thread.currentThread().getName()+" "+index);
                countDownLatch.countDown();
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("全部执行完毕");
                    executorService.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("我就覅电话");
    }
}
