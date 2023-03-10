package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TestOrder {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                System.out.println("emit thread:"+Thread.currentThread().getName());
                emitter.onNext("parade");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .doOnNext(str -> {
                    System.out.println("doOnNext");
                })
                .doFinally(() -> {
                    System.out.println("doFinally");
                })

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        for (int i = 0; i < 10000000; i++) {
                            System.out.print("");
                        }
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        while (true){}
    }
}
