package demo.pattern;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FloatDemoJava {
    public static void main(String[] args) {
        double a=0.6;
        float b=365;
        System.out.println(a/b);
        double f1 = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1+"");
    }
}
