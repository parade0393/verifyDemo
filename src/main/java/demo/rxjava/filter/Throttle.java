package demo.rxjava.filter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * 节流
 * 在某段时间内，只发送该段时间内第1次事件 / 最后1次事件
 * 如，1段时间内连续点击按钮，但只执行第1次（或者最后一次）的点击操作
 */
public class Throttle {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                Thread.sleep(500);

                emitter.onNext(2);
                Thread.sleep(400);

                emitter.onNext(3);
                Thread.sleep(300);

                emitter.onNext(4);
                Thread.sleep(300);

                emitter.onNext(5);
                Thread.sleep(300);

                emitter.onNext(6);
                Thread.sleep(400);

                emitter.onNext(7);
                Thread.sleep(300);

                emitter.onNext(8);
                Thread.sleep(300);

                emitter.onNext(9);
                Thread.sleep(300);

                emitter.onComplete();

            }
        }).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("接收到数据:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("失败");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("成功");
                    }
                });
    }
}
