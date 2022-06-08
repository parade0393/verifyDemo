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
 * delay订阅之后就发送数据，只不过观察者会延迟接收到数据
 * delaySubscription订阅之后延迟发送数据，发送了数据之后，下游立马就接收到数据
 */
public class Delay {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                System.out.println("开始发送数据");//这里开始
                emitter.onNext(1);
//                System.out.println("delay发送了数据1");
                emitter.onNext(2);
//                System.out.println("delay发送了数据2");
                emitter.onComplete();
            }
        })
                .delay(3, TimeUnit.SECONDS,true)//是否延迟发送异常，如果这里为false，下面onNext压根就收不到信息，为true会收到1和2的事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        System.out.println("delay开始订阅了");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
//                        System.out.println("delay发送数据了"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        System.out.println("delay异常:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
//                        System.out.println("delay完成");
                    }
                });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                System.out.println("delaySubscription开始发送数据");
                emitter.onNext(1);
                System.out.println("delaySubscription发送了数据1");
                emitter.onNext(2);
                System.out.println("delaySubscription发送了数据2");
                emitter.onComplete();
            }
        })
                .delaySubscription(3, TimeUnit.SECONDS)//是否延迟发送异常，如果这里为false，下面onNext压根就收不到信息，为true会收到1和2的事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("delaySubscription开始订阅");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("delaySubscription接收到了数据"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("delaySubscription异常:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("delaySubscription完成");
                    }
                });
        while (true){

        }
    }
}
