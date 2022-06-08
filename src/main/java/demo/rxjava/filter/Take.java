package demo.rxjava.filter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 指定观察者能接收的事件的数量，被观察者发送的事件的数量没有限制
 * 取前几条数据或者后几条数据，超出范围不会报错，比如要取前5个，但是一共只有2个，那么下游就接收到2个
 */
public class Take {
    public static void main(String[] args) {
        Observable.just(1,2)
                .take(4)
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

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
    }
}
