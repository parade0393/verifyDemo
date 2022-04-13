package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * debounce 操作符
 * 当一个事件发出来之后，在约定的时间没有再次发送事件，则发送这个事件；
 * 如果发送了新的事件则重新计算时间
 * 注意onComplete之前的一个事件肯定会发送到下游，onComplete事件也肯定会发送到下游
 */
public class RDebounce {
    public static void main(String[] args) {
         Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(1);//因为防斗时间是500ms,而发送1和发送2间隔400ms，所以1事件就丢弃了
                Thread.sleep(505);//发送了2事件就重新计算时间，而在防斗500ms以内没有发送新的事件，所以2事件被发送到下游
                emitter.onNext(1);
                Thread.sleep(100);
                emitter.onNext(1);
                Thread.sleep(605);
                emitter.onNext(1);
                Thread.sleep(100);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println(integer);//2 4 5
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
        while (true){

        }
    }
}
