package demo.thread.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 和AtomicReference不同的是它不仅比较值，还比较版本号，版本号不一样，则修改成功
 */
@Slf4j(topic = "parade0393")
public class CasReferenceStamp {
   static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
    public static void main(String[] args) {
        log.debug("main start");
        //获取值
        String prev = ref.getReference();
        //获取版本号
        int stamp = ref.getStamp();
        other();
        try {
            Thread.sleep(1000);
            log.debug("change A-> C {}",ref.compareAndSet(prev,"C",stamp,stamp+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static void other(){

        new Thread(() -> {
            int stamp = ref.getStamp();
            log.debug("change A-B {}",ref.compareAndSet(ref.getReference(), "B",stamp, stamp +1));
        }).start();
        try {
            Thread.sleep(500);
            int stamp = ref.getStamp();
            log.debug("change B-A {}",ref.compareAndSet(ref.getReference(), "A", stamp, stamp +1));;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
