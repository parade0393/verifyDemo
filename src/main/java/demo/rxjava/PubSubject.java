package demo.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

/**
 * Subject 同时继承了 Observable 和 Observer 两个接口，
 * 说明它既是被观察的对象，同时又是观察对象，也就是可以生产、可以消费、也可以自己生产自己消费。
 *
 * 在Android中通常用来优化搜索 在外部发送事件，限制发送频率
 * 使用debounce操作符，默认是在计算线程接收数据
 */
public class PubSubject {
    public static void main(String[] args) {
        PublishSubject<Long> publishSubject = PublishSubject.create();
         publishSubject.debounce(500, TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(aLong);//2 4 5
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });



        try {
            publishSubject.onNext(1L);
            Thread.sleep(300);
            publishSubject.onNext(2L);
            Thread.sleep(520);
            publishSubject.onNext(3L);
            Thread.sleep(100);
            publishSubject.onNext(4L);
            Thread.sleep(600);
            publishSubject.onNext(5L);
            Thread.sleep(200);
            publishSubject.onComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
