package demo.rxjava.assit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * do系列操作符
 * doOnEach()：含onNext,onComplete和onError，都在之前，比doOnNext还要早
 * doOnNext之前onNext之前调用
 * doOnAfterNext
 * doOnError,在onError之前
 * doOnComplete
 * doOnTerminate正常失败都会调用，不过是在之前
 * doOnFinally正常失败都会调用，不过是在之后
 * doOnSubscribe
 * doOnUnsubscribe
 * 线程切换总结
 * 1.不进行线程Scheduler切换控制时都是在当前主线程里面运行
 * 2.subscribeOn() 的线程控制可以从事件发出的开端就造成影响，当使用多个subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。  所谓开端 指的就是订阅开始
 * 订阅开始后执行的第一个方法是onSubscribe，它永远是当前线程不受任何操作符的影响,如果使用了subscribeOn()，而没使用ObserveOn(),那么整个流程都是在subscribeOn()指定的线程中执行的
 * 3.ObserveOn()控制的是后面的线程，可以多个ObserveOn()一起使用。每使用一次ObserveOn()，它后面的操作的线程就跟着变换一次
 * 4.对于设置doOnSubscribe回调默认跟onSubscribe的回调规则一样，那样在执行subscribe()订阅动作的时候被调用了，但是如果在 doOnSubscribe() 之后有 subscribeOn() ，它将执行在离它最近的 subscribeOn() 所指定的线程。(onSubscribe不受影响)
 * 5. 发送数据的线程和第一个subscribeOn()指定的线程一致，如果没有使用subscribeOn()那就和当前创建被观察者所在的线程一致
 * //执行顺序
 * 同一线程:doOnSubscribe>onSubscribe;不同线程:onSubscribe>doOnSubscribe 一般onSubscribe都是在当前线程(主线程)
 * 1.doOnEach在每个对应的事件之前执行>doOnNext或者大于doOnComplete或者大于doOnError
 * 2.doOnNext和doAfterNext和onNext的执行顺序：同一线程下(doAfterNext和onNext比较)doOnNext>onNext>doAfterNext,不同线程下doOnNext>doAfterNext>onNext,如果有同一个线程有多个doAfterNext，则从后向前执行
 * 3.doOnNext>doOnComplete>onNext>doOnTerminate>onComplete>doFinally
 * 4.doOnError>doOnTerminate>onError>doFinally
 */
public class DoOrder {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                System.out.println("发送数据时所在的线程:"+Thread.currentThread().getName());
                emitter.onNext(1);
//                emitter.onComplete();
                emitter.onError(new Throwable("发生错误"));
            }
        })
                .observeOn(Schedulers.io())
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Throwable {
                        System.out.println("doOnEach执行了:"+integerNotification.toString()+":所在线程:"+Thread.currentThread().getName());
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("doOnComplete执行了:所在线程"+Thread.currentThread().getName());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        System.out.println("doOnError执行了:"+throwable.getMessage()+"：所在线程:"+Thread.currentThread().getName());
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("第一次doOnNext:" + Thread.currentThread().getName());//第一次onNextRxCachedThreadScheduler-1
                    }
                })
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("第一次doAfterNext:" + Thread.currentThread().getName());
                    }
                })
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("第二次doAfterNext:" + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("doOnTerminate执行了：所在线程:"+Thread.currentThread().getName());
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("doFinally执行了:所在线程:"+Thread.currentThread().getName());
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("第二次doOnNext:" + Thread.currentThread().getName());//第二次onNextRxComputationThreadPool-1
                    }
                }).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("第三次doAfterNext:" + Thread.currentThread().getName());//第二次onNextRxComputationThreadPool-1
            }
        }).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("第四次doAfterNext:" + Thread.currentThread().getName());//第二次onNextRxComputationThreadPool-1
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("第三次doOnNext:" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.computation()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe所在线程:"+Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext:" + integer+"：所在线程"+Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError执行了:所在线程:"+Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete执行了:所在线程:"+Thread.currentThread().getName());
            }
        });
        while (true) {
        }
    }
}
