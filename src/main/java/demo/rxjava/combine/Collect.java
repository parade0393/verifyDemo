package demo.rxjava.combine;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将被观察者Observable发送的事件收集到一个数据解构里，Java8的stream也有类似的函数
 * collect返回的是一个single包裹的被观察者，而不是一个普通的Observable
 * 可以指定一个空的数据接口，也可以指定一个已有的数据解构(在accept里需要手动添加需要添加的数据)
 * collectInto的区别是需要在accept里判断哪些不需要添加到新的数据解构中去
 */
public class Collect {
    public static void main(String[] args) {
        Observable.just(1,2,3,4,5)
                .collect(Collectors.toList())
                .subscribe(new SingleObserver<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Integer> integers) {
//                        System.out.println(integers);//[1, 2, 3, 4, 5]
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(11);
        Observable.just(1,2,3,4)
                //指定一个初始容器
                .collect(new Supplier<List<Integer>>() {
                    @Override
                    public List<Integer> get() throws Throwable {
                        return list;
                    }
                }, new BiConsumer<List<Integer>, Integer>() {
                    @Override
                    public void accept(List<Integer> integers, Integer integer) throws Throwable {
                        integers.add(integer);
                    }
                }).subscribe(new SingleObserver<List<Integer>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Integer> integers) {
//                System.out.println("收到"+integers);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        Observable.just(1,2,3,4)
                .collectInto(list, new BiConsumer<List<Integer>, Integer>() {
                    @Override
                    public void accept(List<Integer> integers, Integer integer) throws Throwable {
                          if (integer == 2){
                              integers.remove(integer);
                          }
                    }
                })
                .subscribe(new SingleObserver<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Integer> integers) {
                        System.out.println("收到"+integers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
