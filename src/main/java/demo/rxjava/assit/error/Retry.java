package demo.rxjava.assit.error;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 无限重试
 * 当出现错误时，让被观察者（Observable）重新发射数据
 * 观察者会重复接收到数据1和2，不会接收到error事件
 */
public class Retry {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable(""));
            }
        }).retry().subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("接收到数据:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("失败了:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("成功了");
            }
        });
    }
}
