package demo.time;

import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        long timestamp = 1680510409;// 0时区的时间戳
        long currentTimeZoneStamp = timestamp - TimeZone.getDefault().getRawOffset();//北京时间比0时间要早，所以要减去
        System.out.printf(currentTimeZoneStamp+"");
    }
}
