package demo.thread.cas.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayDemo {
    public static void main(String[] args) {
        demo(
                ()->new int[10],
                array -> array.length,
                (array,index) -> array[index]++,
                array-> System.out.println(Arrays.toString(array))
        );
        demo(
                ()->new AtomicIntegerArray(10),
                array -> array.length(),
                (array,index) -> array.getAndIncrement(index),
                array -> System.out.println(array)
        );
    }

    /**
     * @param arraySupplier 提供数组
     * @param lengthFunction 提供数组的长度
     * @param putConsumer 自增方法 回传array，index
     * @param printConsumer 打印数组
     */
    private static <T> void  demo(
            Supplier<T> arraySupplier,
            Function<T,Integer> lengthFunction,
            BiConsumer<T,Integer> putConsumer,
            Consumer<T> printConsumer
    ){
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int length = lengthFunction.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array,j%length);
                }
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printConsumer.accept(array);
    }
}
