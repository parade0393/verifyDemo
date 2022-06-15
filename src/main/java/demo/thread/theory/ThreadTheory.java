package demo.thread.theory;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示多个线程并行执行
 * 单核CPU不适用于这个demo，CPU会被一直占用
 */
@Slf4j(topic = "parade")
public class ThreadTheory {
    public static void main(String[] args) {
        new Thread(()->{while (true){log.debug("running");}},"t1").start();
        new Thread(()->{while (true){log.debug("running");}},"t2").start();
    }
}
