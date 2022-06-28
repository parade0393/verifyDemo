package demo.rxjava.combine;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 组合多个被观察者一起发送数据，合并后 按时间线并行执行,
 * 1.不同线程还是会按照顺序顺出的
 * 2.如果使用了延时操作就不会安顺序输出了
 */
public class Merge {
    public static void main(String[] args) {
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(10));
        list.add(Observable.just(20));
        list.add(Observable.just(30));
        list.add(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                emitter.onNext(40);
//                emitter.onComplete();
                emitter.onError(new Throwable("数据错误"));
            }
        }));
        list.add(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                emitter.onNext(50);
//                emitter.onComplete();
                emitter.onError(new Throwable("又一个数据错误"));
            }
        }));
        list.add(Observable.just(60));
        list.add(Observable.just(70));
        list.add(Observable.just(80));
        list.add(Observable.just(90));
        //内部调用了fromArray(source1, source2, source3).flatMap((Function)Functions.identity(), false, 3);
        Observable.merge(Observable.just(1,2),Observable.fromArray(3,4,5),Observable.fromIterable(Arrays.asList(6,7,8)))
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });

        //内部调用了 fromIterable(sources).flatMap((Function)Functions.identity());
        //一旦有上游有错误，下游不会接收到剩下的事件
        Observable.merge(list).doFinally(new Action() {
            @Override
            public void run() {
                System.out.println("concat接收到事件:finally");
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("接收到了"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("接收到了完成");
                    }
                });

        //内部调用了fromArray(sources).flatMap((Function)Functions.identity(), sources.length);和merge一样，只是长度不一样
        Observable.mergeArray(Observable.just(1),Observable.just(2),Observable.just(3),Observable.just(4),Observable.just(5),Observable.just(6))
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("mergeArray:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("concatArray:完成");
            }
        });

        //内部调用了fromIterable(sources).flatMap((Function)Functions.identity(), true);
        Observable.mergeDelayError(list).subscribeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("mergeDelayError接收到了doFinally事件");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("mergeDelayError:"+integer+"-"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //如果有两个以上的错误，会合并成一个CompositeException
//                        System.out.println("concatDelayError"+e.getMessage());//2 exceptions occurred.
                        if (e instanceof CompositeException){
                            CompositeException exception = (CompositeException) e;
                            for (int i = 0; i < exception.getExceptions().size(); i++) {
                                System.out.println("mergeDelayError:"+exception.getExceptions().get(i).getMessage());
                            }
                        }else {
                            System.out.println("mergeDelayError接收到了错误"+e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("mergeDelayError:完成");
                    }
                });

        //内部调用了 fromArray(sources).flatMap((Function)Functions.identity(), true, sources.length);和merge比较多了一个参数
        Observable.mergeArrayDelayError(Observable.just(1),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("mergeArrayDelayError发生了错误"));
            }
        }),Observable.just(3),Observable.just(4),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("mergeArrayDelayError哟有有发生了错误"));
            }
        }),Observable.just(6))
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("mergeArrayDelayError接收到事件："+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof CompositeException){
                            CompositeException exception = (CompositeException) e;
                            for (Throwable exceptionException : exception.getExceptions()) {
                                System.out.println("mergeArrayDelayError:"+exceptionException.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.merge(Observable.intervalRange(0,3,1,1, TimeUnit.SECONDS)
        ,Observable.intervalRange(4,3,1,1,TimeUnit.SECONDS))

                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        System.out.println("延时接收到"+aLong);//4,0,5,1,6,2或者其他的顺序不一定
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("延迟接收到错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        while (true){}
    }
}
