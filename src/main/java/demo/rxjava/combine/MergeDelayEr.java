package demo.rxjava.combine;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MergeDelayEr {
    public static void main(String[] args) {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onError(new Throwable("出错了"));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("a");
//                System.out.println("a发送了");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable3 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("b");
//                System.out.println("发送了b");
                emitter.onComplete();
//                System.out.println("b发送完成");
            }
        }).subscribeOn(Schedulers.io());

        List<Observable<String>> list = new ArrayList<>();
        list.add(observable1);
        list.add(observable2);
        list.add(observable3);
        Observable.mergeDelayError(list)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("收到数据-------:"+s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("发生错误："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        while (true){

        }
    }
}
