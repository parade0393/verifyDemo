package demo.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * fromArray 和 just 两个 操作符内部是相通的,根据传入的参数的数量不同可以进行相互的转化
 */
public class Just {
    public static void main(String[] args) {
        Observable.just(1,2,3,4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }
}
