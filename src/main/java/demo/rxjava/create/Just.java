package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 快速创建被观察者
 * 直接发送传入的事件
 * 最多可以发送10个事件
 * just发送1个以上的事件时，又重新调用了fromArray
 */
public class Just {
    public static void main(String[] args) {
        //相当于onNext(1),onNext(2),onNext(3),onNext(4),onComplete()
        Observable.just(1,2,3,4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开始订阅");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到事件:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });
    }
}
