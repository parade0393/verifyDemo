package demo.thread.immute;

import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Slf4j(topic = "parade0393")
public class MutableTest {
    public static void main(String[] args) {
        //可变类是线程不安全的
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                log.debug("{}",simpleDateFormat.parse("1951-04-21"));
//                    Date parse = simpleDateFormat.parse("1951-04-21");
            }).start();
        }
    }
}
