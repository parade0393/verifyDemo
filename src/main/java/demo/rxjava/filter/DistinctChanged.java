package demo.rxjava.filter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 只过滤连续重的数据
 * 此例子中第3个数据3和第4个数据3是重复的，只留一个
 * 最后两个5是重复的，只留一个
 */
public class DistinctChanged {
    public static void main(String[] args) {
        Observable.just(1,2,3,3,1,4,5,4,5,5)
                .distinctUntilChanged()
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
