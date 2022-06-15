package demo.calendar;

import java.util.Calendar;

public class CalendarDemo {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.HOUR_OF_DAY);
        int i1 = instance.get(Calendar.MINUTE);
        System.out.println("时间:"+i);
        System.out.println("分钟:"+i1);
    }
}
