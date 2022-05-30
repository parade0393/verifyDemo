package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * 和interval类似
 * 指定发送数量，发送了指定数量的事件之后会发送onComplete事件
 * 可指定起始事件
 */
public class IntervalRange {
    public static void main(String[] args) {
        Observable.intervalRange(3,10,1,1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("接收到了:"+aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成了");
            }
        });
        System.out.println("主线程");
        while (true){}
    }
}
