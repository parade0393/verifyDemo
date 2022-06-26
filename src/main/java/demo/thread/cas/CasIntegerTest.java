package demo.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 针对大数字的累加，jdk8之后提供了累加器LongAdder，LongAccumulate等等
 */
public class CasIntegerTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
//        int i = atomicInteger.incrementAndGet();//++i
//        int andIncrement = atomicInteger.getAndIncrement();//i++
//        atomicInteger.addAndGet(2);//++2
//        atomicInteger.getAndAdd(2);//2++

//        int i = atomicInteger.updateAndGet(x -> x * 2);//10
        int andUpdate = atomicInteger.getAndUpdate(x -> x * 2);//5 atomicInteger是10
    }
}
