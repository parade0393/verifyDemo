package demo.rxjava.combine;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
 */
public class StartWithAndArray {
    public static void main(String[] args) {
        Observable.just(1,2,3)
                .startWithItem(0)
                .startWithArray(4,5,6)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
//                        System.out.println("接收到了事件："+integer);//4560123
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        System.out.println("完成");
                    }
                });

        Observable.just(1,2)
                .startWith(Observable.just(4,5,6))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("接收到了事件："+integer);//45612
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
