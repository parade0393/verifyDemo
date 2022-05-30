package demo.rxjava.create;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * 相当于Js的setInterval
 * 创建一个被观察者对象，每隔指定的事件，发送一次事件
 * 发送的事件序列(从0开始，无限递增1的整数序列)，如果需要中断这种操作，需要配合其他操作符
 * 定时的操作应该也是在子线程(计算线程)中操作的。
 * 参数可传2个或者三个，传3个的时候第一个参数代表第一次执行的延迟时间，传两个参数，代表第第一个的延迟时间和后面的延迟时间一样
 */
public class Interval {
    public static void main(String[] args) {


        Disposable subscribe1 = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                System.out.println(aLong);
            }
        });

        while (true){}
    }
}
