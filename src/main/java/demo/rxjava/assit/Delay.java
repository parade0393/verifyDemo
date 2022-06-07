package demo.rxjava.assit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * 延迟操作有很多重载的方法
 * 默认在计算线程延迟，可以通过重载方法指定调度器
 * delay立即订阅延迟发送
 * delaySubscription延迟订阅，立即发送
 * 区别有待确认
 */
public class Delay {
    public static void main(String[] args) {
        Observable.concat(Observable.just(1,2),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("发生错误"));
            }
        }),Observable.just(4,5))
                .delay(3, TimeUnit.SECONDS,true)//是否延迟发送异常，如果这里为false，下面onNext压根就收不到信息，为true会收到1和2的事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        System.out.println("开始");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
//                        System.out.println(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        System.out.println("异常:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
//                        System.out.println("完成");
                    }
                });

        Observable.concat(Observable.just(1,2),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Thread.sleep(1000);
                emitter.onNext(3);
            }
        }),Observable.just(4,5))
                .delaySubscription(3, TimeUnit.SECONDS)//是否延迟发送异常，如果这里为false，下面onNext压根就收不到信息，为true会收到1和2的事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("开始");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("异常:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
        while (true){

        }
    }
}
