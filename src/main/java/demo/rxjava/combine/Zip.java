package demo.rxjava.combine;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 合并 多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
 * 1.事件组合方式 = 严格按照原先事件序列 进行对位合并
 * 2.最终合并的事件数量 = 多个被观察者（Observable）中数量最少的数量
 *
 * 如果在发送了onError事件之后继续发送了事件会抛出UndeliverableExceptionThe异常，或者在onComplete之后发送了onError事件
 * 此时观察者接收不到异常事件，只能通过 RxJavaPlugins.setErrorHandler来接收异常
 */
public class Zip {
    public static void main(String[] args) {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("被观察者1发送了事件1");
                emitter.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);

                System.out.println("被观察者1发送了事件2");
                emitter.onNext(2);
                Thread.sleep(1000);

                System.out.println("被观察者1发送了事件3");
                emitter.onNext(3);
                Thread.sleep(1000);
                System.out.println("被观察者1发送了事件onComplete");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 设置被观察者1在工作线程1中工作

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("被观察者2发送了事件A");
                emitter.onNext("A");
                Thread.sleep(1000);
                emitter.onError(new Throwable("被观察者2自定义错误"));
                System.out.println("被观察者2发送了事件B");
                emitter.onNext("B");
                Thread.sleep(1000);

                System.out.println("被观察者2发送了事件C");
                emitter.onNext("C");
                Thread.sleep(1000);

                System.out.println("被观察者2发送了事件D");
                emitter.onNext("D");
                Thread.sleep(1000);
                System.out.println("被观察者2发送了事件onComplete");
//                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());// 设置被观察者2在工作线程2中工作
        // 假设不作线程控制，则该两个被观察者会在同一个线程中工作，即发送事件存在先后顺序，而不是同时发送

// 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                return integer + string;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println("最终接收到的事件 =  " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                if (throwable instanceof UndeliverableException){
                    UndeliverableException undeliverableException = (UndeliverableException) throwable;
                    System.out.println("UndeliverableException"+undeliverableException.getMessage());
                }
            }
        });

        while (true) {
        }

    }
}
