package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 *mergeArrayDelayError会等待所有的观察者发送完事件(onNext)才发送onError事件
 */
public class MergeDelayError {
    public static void main(String[] args) {
        Observable.mergeArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("模拟失败了"));
            }
        }),Observable.just(2,3,4))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        System.out.println("开始");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("结束");
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Throwable {
                        return integer*2;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer s) {
                        System.out.println("成功了："+s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("失败了："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
