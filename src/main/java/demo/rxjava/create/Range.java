package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 和interval类似，只不过没有延迟发送，会立即发送
 * 这个操作符默认是阻塞当前线程的
 * 下面代码最后输出 "主线程"
 * count=1的时候会调用just
 * 数据类型是Integer，所以发送的数据数量不能超过Integer的最大值，如果特殊需求，数量需要超过Integer的最大值，可使用rangeLong
 */
public class Range {
    public static void main(String[] args) {
        Observable.range(1,10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到了数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });

        System.out.println("主线程");
    }
}
