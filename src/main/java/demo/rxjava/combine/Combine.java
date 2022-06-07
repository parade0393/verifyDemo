package demo.rxjava.combine;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * 本例子讲述了combineLatest，combineLatestDelayError（）和其他DelayError原里一样，就是延迟处理error事件，等所有的正常事件发送完毕再发送error事件
 * combine相关自动是在子线程合并的
 * 当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
 * 与Zip（）的区别：Zip（） = 按个数合并，即1对1合并；CombineLatest（） = 按时间合并，即在同一个时间点上合并
 */
public class Combine {
    public static void main(String[] args) {
        Observable.combineLatest(
                Observable.just(1L, 2L, 3L,4L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 5, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        System.out.println("合并的数据是： "+ o1 + " "+ o2);
                        return o1 + o2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                System.out.println( "合并的结果是： "+s);//第二个发送多少个事件，这里就会接收到多少个事件
            }
        });

        while (true){

        }

    }
}
