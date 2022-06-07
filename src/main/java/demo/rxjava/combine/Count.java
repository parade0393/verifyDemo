package demo.rxjava.combine;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 统计被观察者发送事件的数量，返回的是Single
 */
public class Count {
    public static void main(String[] args) {
        Observable.just(1,2,3)
                .count()
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        System.out.println("发送的事件数量:"+aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
