package demo.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * timer操作符
 * 用来延迟发送一个特殊的值，只是单纯的用来做延时操作
 * 和delay的区别 ：其实他俩的功能是一样的
 * 只是time的是用于创建Observable的和just，from级别是一样的
 * delay是用于流中的操作和map()，flatMap()的级别是一样的
 */
public class Normal {
    public static void main(String[] args) {
        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                System.out.println(aLong);
            }
        });
        while (true){

        }
    }
}
