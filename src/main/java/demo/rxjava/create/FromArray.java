package demo.rxjava.create;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 还有一个操作符是fromIterable和fromArray使用差不多，这种既可以保证数据的顺序，又能保证请求的整体性(都完成了整体完成)，但是某一个失败，则整体失败
 * 快速创建被观察者
 * 直接发送传入的事件
 * 发送数量不受限制
 * 发送的数据类型为数组，若传入一个list,则相当于发送了一个事件
 * 发送1个事件的时候内部又调用了just
 * 可以用来遍历数组
 */
public class FromArray {
    public static void main(String[] args) {
        Observable.fromArray(1,2,3)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        System.out.println("开始");
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Throwable {
                        if (integer == 2){
                            Integer temp = integer;
                            for (int i = 0; i < 100000000; i++) {
                                temp++;
                            }
                            return Observable.error(new Throwable("模拟失败"));
//                            return Observable.just(temp + "附加");
                        }else{
                            return Observable.just(integer + "是的");
                        }

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("结束");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("成功了："+s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        System.out.println("失败了"+throwable.getMessage());
                    }
                });

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
