package demo.rxjava.assit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * 只有在上游发送了onComplete()事件之后才会重复不断地发送被观察者事件
 * 重载方法和retry类似，也有repeatWhen和repeatUtil
 */
public class RepeatAndRepeatWhen {
    public static void main(String[] args) {
        Observable.<Integer>create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
//                emitter.onError(new Throwable("error"));
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {
//                        return Observable.empty();//发送onComplete事件，但不会回调观察者的onComplete()
//                        return Observable.never();
//                        return Observable.error(new Throwable("NEW "));//返回error事件，回调观察者的onError方法
                        return Observable.just(10);//只要不是onError或者onComplete事件都会触发重复发送，观察者不会接收到这里发送的数据
                    }
                });
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到了是数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("接收到了异常："+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });
    }
}
