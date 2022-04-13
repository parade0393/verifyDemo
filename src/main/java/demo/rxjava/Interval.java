package demo.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

import java.util.concurrent.TimeUnit;

/**
 * interval 操作符
 * 循环发送数据
 */
public class Interval {
    public static void main(String[] args) {

        //每隔1s发送一次数据
        Disposable subscribe1 = Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Throwable {
                        return aLong == 10;//返回true就不再发送
                    }
                })
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                System.out.println(aLong);
            }
        });

        while (true){}
    }
}
