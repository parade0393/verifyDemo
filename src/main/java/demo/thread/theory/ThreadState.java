package demo.thread.theory;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "parade")
public class ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
               log.debug("running");
               log.debug("state3:{}",getState());//RUNNABLE
            }
        };
        log.debug("state1:{}",thread.getState());//NEW
        thread.start();
        log.debug("state2:{}",thread.getState());//RUNNABLE
    }
}
