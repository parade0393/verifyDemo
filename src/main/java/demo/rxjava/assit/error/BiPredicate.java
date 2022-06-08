package demo.rxjava.assit.error;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * retry的重载方法和两个参数的重载功能差不多
 * 只不过这个重试次数不是自己设置的，每重试一次，传入的重试次数就会加1，如果一开始就不需要重试，那么retry就只会执行一次
 */
public class BiPredicate {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("ERROR"));
            }
        }).retry(new io.reactivex.rxjava3.functions.BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Throwable {
                System.out.println(integer);
                return true;
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
