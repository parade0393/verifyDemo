package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Supplier;

/**
 * 直到有观察者订阅时，才动态创建被观察者对象并发送事件
 * 可以保证获取的是最新的数据
 */
public class Defer {

    public static void main(String[] args) {
        final String[] name = {"parade"};
        Observable<String> defer = Observable.defer(new Supplier<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> get() throws Throwable {
                return Observable.just(name[0]);
            }
        });
        name[0] = "0393";
        defer.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开始订阅");
            }

            @Override
            public void onNext(@NonNull String string) {
                System.out.println(string);//每次订阅的时候都会创建一个最新的被观察者对象，保证Observable里的数据是最新的
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
