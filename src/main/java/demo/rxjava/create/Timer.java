package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * 相当于Js的setTimeOut
 * 创建一个被观察者对象，延迟指定时间后发送一个数值0
 * 延迟的操作应该是在子线程操作的，所以如果在延迟时间到之前，主线程已经结束了，观察者就接收不到事件了
 */
public class Timer {
    public static void main(String[] args) {
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("开始订阅");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("收到数据:"+aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
