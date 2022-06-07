package demo.rxjava.combine;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;

/**
 * 把被观察者需要发送的事件聚合成1个事件 & 发送
 * 和js的reduce差不多，可以有初始值
 */
public class Reduce {
    public static void main(String[] args) {
        Observable.just(1,2,3,4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
                        //第一次是前两个数
//                        //第二次是第一次的返回值加第三个数
//                        System.out.println(""+integer+",,"+integer2);
                        return integer+integer2;
                    }
                }).subscribe();

        Observable.just(1,2,3,4)
                .reduce(10, new BiFunction<Integer, Integer, Integer>() {
                    //这个可以指定初始值
                    //第一次是初始值和第一个值
                    //从第二次开始就是前一次的结果和后一个数
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
//                        System.out.println(""+integer+",,"+integer2);
                        return integer+integer2;
                    }
                })
                .subscribe();
        Observable.just(1,2,3,4)
                //初始值可以通过函得到
                .reduceWith(new Supplier<Integer>() {
                    @Override
                    public Integer get() throws Throwable {
                        return 5;
                    }
                }, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
                        System.out.println(""+integer+",,"+integer2);
                        return integer+integer2;
                    }
                }).subscribe();
    }
}
