package demo.rxjava.assit.error;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class RetryWhen {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("error"));
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Throwable {
                //参数为上游抛出的异常
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                        System.out.println("上游抛出的异常:"+throwable.getMessage());
//                        return Observable.error(new Throwable("新产生的异常"));//返回error事件，则不会重试，下游将接收到这里新产生的异常
                        return Observable.just(3);//返回成功事件，则会重试，上游会持续发送事件
                    }
                });
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
                System.out.println("失败了:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("成功了");
            }
        });
    }
}
