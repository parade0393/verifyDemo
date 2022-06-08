package demo.rxjava.assit.error;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BooleanSupplier;

/**
 * 和retry的一个参数的Predicate功能差不多
 * 不过和retry的逻辑刚好想法
 */
public class RetryUntil {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("error"));
            }
        }).retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Throwable {
                return false;//false的时候就一直重试，true的时候不再重试
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
