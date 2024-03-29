package demo.rxjava.transfer;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 *将事件序列中的每个事件都转成一个Observable并把每个原始事件转换后的新事件都放入到对应的Observable对象
 *将每个新建的Observable对象都合并到一个新建的、总的Observable对象中
 * 新建的总的Observable对象将新合并的事件序列发送给观察者
 * 1.在不同线程 flatMap是无序的
 * 新合并生成的事件序列是无序的，即与旧序列发送事件的的顺序无关
 * 2.在相同线程事件是有序的，按照原始事件序列
 */
public class FlatmapTransfer {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

       Observable.fromIterable(list).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Throwable {
                return Observable.just(integer + "").subscribeOn(Schedulers.io());
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
         }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });
        while (true){}
    }
}
