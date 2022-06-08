package demo.rxjava.filter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 根据索引过滤
 */
public class PositionFilter {
    public static void main(String[] args) {
        Observable.just(1,2,3)
                .elementAtOrError(5)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        System.out.println("接收到数据:"+integer);//超出索引elementAt这里不会回调
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("错误了:"+e.getMessage());//超出索引elementAt这里不会回调 ,elementAtOrError会回调这里
                    }
                });
    }
}
