package demo.rxjava.assit.error;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * 遇到错误时，发送1个特殊事件 & 正常终止
 * 也可以直接使用onErrorReturnItem返回一个数据
 */
public class ErrorReturn {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发送错误"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                System.out.println("在onErrorReturn处理异常:"+throwable.getMessage());
                return 0;//发生错误时间后，发送一个0正常结束，相当于又发送了一个0和一个onComplete事件
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError:"+e.getMessage());//不会调用
            }

            @Override
            public void onComplete() {
                System.out.println("完成");//会调用
            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("我");
                emitter.onNext("是");
                emitter.onError(new Throwable("模拟错误"));
            }
        }).onErrorReturnItem("备用")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("这个接到了:"+s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("这个错误了");//不会调用
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("这个完成了");//会
                    }
                });
    }
}
