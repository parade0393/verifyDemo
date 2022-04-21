package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * rxjava正常使用
 * dispose之后上游还会发送事件，只是下游接收不到了
 */
public class Test {
    public static void main(String[] args) {
        final Disposable[] disposable = {null};
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                disposable[0].dispose();
                System.out.println("ertyurtrytytyuiu");
                emitter.onNext("4");
                emitter.onNext("5");
                emitter.onNext("6");
                emitter.onComplete();
                System.out.println("1234567");
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable[0] = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
//        disposable[0].dispose();
        while (true){

        }
    }
}
