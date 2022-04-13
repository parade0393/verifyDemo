package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * rxJava延时操作
 * delay操作符
 * 发送事件之后延迟指定的时间下游才能接收到事件，然后才发送complete事件
 * 使用delay操作符，默认是在计算线程中接收事件RxComputationThreadPool-1
 */
public class Delay {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                System.out.println("发送："+Thread.currentThread().getName());
                System.out.println("开始发送");
                emitter.onNext(1);//立马发送，但是下游延迟指定的时间后才能接收到事件
                System.out.println("发送了");
                emitter.onComplete();
            }
        }).delay(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("开始订阅");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("onNext:"+Thread.currentThread().getName());
                        System.out.println("接收到数据:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("发送事件完成");
                    }
                });

        while (true){//如果不加这个，主线程结束了就看不到打印的结果了

        }
    }
}
