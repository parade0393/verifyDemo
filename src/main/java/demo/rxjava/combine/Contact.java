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

/**
 * contact最多可以组合4个被观察者，contactArray可以发送任意个被观察者，他们都是合并后 按发送顺序串行执行
 * 本demo介绍了
 * 1.contact接收非迭代器参数(最多4个Observable)
 * 2.concat接收迭代器参数(可以无限个，迭代器的元素可以是无限个Observable)
 * 3.contactArray(可以接收多个Observable)
 * 以下对错误处理都是遇到错误下游就会接收不到剩余的事件
 * 4.concatDelayError(迭代器的Observable)，发生错误后下游会接收到所有的事件，有两个以上的错误时onError会合并成一个CompositeException
 * 5.concatArrayDelayError和concatDelayError一样，只是传参不一样
 * 但是使用可迭代容器，则可以发送您任意个Observable
 */
public class Contact {
    public static void main(String[] args) {
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(10));
        list.add(Observable.just(20));
        list.add(Observable.just(30));
        list.add(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                emitter.onNext(40);
                emitter.onError(new Throwable("数据错误"));
            }
        }));
        list.add(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("又一个数据错误"));
            }
        }));
        list.add(Observable.just(60));
        list.add(Observable.just(70));
        list.add(Observable.just(80));
        list.add(Observable.just(90));
        //内部调用了concatArray
        Observable.concat(Observable.just(1,2),Observable.fromArray(3,4,5),Observable.fromIterable(Arrays.asList(6,7,8)))
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

        //内部调用了 fromIterable(sources).concatMapDelayError((Function)Functions.identity(), false, bufferSize());
        //一旦有上游有错误，下游不会接收到剩下的事件
        Observable.concat(list).doFinally(new Action() {
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

        Observable.concatArray(Observable.just(1),Observable.just(2),Observable.just(3),Observable.just(4),Observable.just(5),Observable.just(6))
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("concatArray:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("concatArray:完成");
            }
        });

        //会按照发送顺序，
        Observable.concatDelayError(list).subscribeOn(Schedulers.io())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("concatDelayError接收到了doFinally事件");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("concatDelayError:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                       //如果有两个以上的错误，会合并成一个CompositeException
//                        System.out.println("concatDelayError"+e.getMessage());//2 exceptions occurred.
                        if (e instanceof CompositeException){
                            CompositeException exception = (CompositeException) e;
                            for (int i = 0; i < exception.getExceptions().size(); i++) {
                                System.out.println("concatDelayError:"+exception.getExceptions().get(i).getMessage());
                            }
                        }else {
                            System.out.println("concatDelayError接收到了错误"+e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("concatDelayError:完成");
                    }
                });

        Observable.concatArrayDelayError(Observable.just(1),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("concatArrayDelayError发生了错误"));
            }
        }),Observable.just(3),Observable.just(4),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onError(new Throwable("concatArrayDelayError哟有有发生了错误"));
            }
        }),Observable.just(6))
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("concatArrayDelayError接收到事件："+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof CompositeException){
                            CompositeException exception = (CompositeException) e;
                            for (Throwable exceptionException : exception.getExceptions()) {
                                System.out.println("concatArrayDelayError:"+exceptionException.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        while (true){}
    }
}
