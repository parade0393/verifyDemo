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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeDemo {
    public static void main(String[] args) {
        List<Observable<String>> list = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            list.add(Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                    System.out.println("发送线程："+Thread.currentThread().getName());
                    emitter.onNext(":"+finalI);
                    emitter.onComplete();
                }
            }).subscribeOn(Schedulers.from(service)));
        }

        Observable.mergeDelayError(list).subscribeOn(Schedulers.io())
                .doFinally(service::shutdown)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe:"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("onNext:"+s+"--"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError:"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete:"+Thread.currentThread().getName());
                    }
                });

        while (true){
//            boolean shutdown = service.isShutdown();
//            System.out.println(shutdown);
        }
    }

}
