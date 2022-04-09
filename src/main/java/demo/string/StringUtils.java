package demo.string;

import java.math.BigDecimal;

public class StringUtils {
    public static void main(String[] args) {
        float dd = stripTrailingZero("dd");
        System.out.println(dd);
    }

    /**
     * 检查字符串是否可以转成数字类型
     */
    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }


    public static float stripTrailingZero(String str){
        return  new BigDecimal("6003.00010111").stripTrailingZeros().floatValue();
    }
}
