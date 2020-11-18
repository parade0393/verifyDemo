package demo;

import java.text.DecimalFormat;
import java.util.Locale;

class TestJava {
    public static void main(String[] args) {
        String source = "1254.2";
        double v = Double.parseDouble(source);
        String format = String.format(Locale.getDefault(),"%.2f", v);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String decimalString = decimalFormat.format(v);

        String result = "";

        int i = source.indexOf(".");
        int length = source.substring(i + 1).length();
        if (length>2){
            result = format;
        }else {
            result = source;
        }
        System.out.println(result);

    }
}
