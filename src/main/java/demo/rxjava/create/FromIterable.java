package demo.rxjava.create;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速创建被观察者
 * 直接发送传入的事件
 * 发送数量不受限制
 * 发送的数据类型是可迭代对象
 * 和just还有fromArray没有关系
 */
public class FromIterable {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        //相当于onNext("1"),onNext("2"),onNext("3"),onNext("4"),onComplete()
        Observable.fromIterable(list).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开始创建:");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("接收到数据:"+s);
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
