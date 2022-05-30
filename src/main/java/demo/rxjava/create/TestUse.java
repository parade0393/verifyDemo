package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * empty仅发送onComplete事件，直接通知完成
 * error仅发送error事件，直接通知失败
 * never不发送任何事件
 */
public class TestUse {
    public static void main(String[] args) {
        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开始订阅");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("接收到数据");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("成功");
            }
        });
    }
}
