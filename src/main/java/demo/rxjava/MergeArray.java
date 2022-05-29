package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * mergeArray和contactArray都是合并多个请求
 * mergeArray可以并行请求，contactArray是串行请求
 * 但是存在同样的问题，某个接口出错了，会影响剩余的接口发送数据
 */
public class MergeArray {
    public static void main(String[] args) {
        Observable.mergeArray(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onError(new Throwable("哈哈，出错了"));
            }
        }),Observable.just("1","2","3"))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        System.out.println("开始");
                    }
                })
//                .flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Throwable {
//                        if (integer == 2){
//                            Integer temp = integer;
//                            for (int i = 0; i < 100000000; i++) {
//                                temp++;
//                            }
//                            return Observable.error(new Throwable("模拟失败"));
////                            return Observable.just(temp + "附加");
//                        }else{
//                            return Observable.just(integer + "是的");
//                        }
//
//                    }
//                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("结束");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("成功了："+s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        System.out.println("失败了"+throwable.getMessage());
                    }
                });

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
