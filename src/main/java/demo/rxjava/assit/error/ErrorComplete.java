package demo.rxjava.assit.error;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;

/**
 * onErrorComplete会把错误干掉，而换成发送一个onComplete事件
 */
public class ErrorComplete {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onError(new Throwable("error"));
            }
        }).onErrorComplete().subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到了数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误了");//不会调用
            }

            @Override
            public void onComplete() {
                System.out.println("完成了");//会调用
            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onError(new Throwable("again error"));
            }
        }).onErrorComplete(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Throwable {
                return false;//返回true，观察者接受到成功回调，返回false观察者接收到失败回调
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("失败回调"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("成功回调");
            }
        });
    }
}
