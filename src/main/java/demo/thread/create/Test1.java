package demo.thread.create;

import lombok.extern.slf4j.Slf4j;

/**
 *最好为线程都起一个名字
 */
@Slf4j(topic = "parade")
public class Test1 {
    public static void main(String[] args) {
        Thread thread = new Thread("demo") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        thread.start();
//        thread.start();//start只能调用一次
        log.debug("running");
    }
}
