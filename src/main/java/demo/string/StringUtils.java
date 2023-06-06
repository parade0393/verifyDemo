package demo.string;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static void main(String[] args) {
        String url = getParamByUrl("xgscheme://com.zwt.push/workplatform_web?type=102&id=79&url=http%3a%2f%2f218.29.175.190%3a8090%2fHtml%2foa%2f6447%2f5.2.3.68%2fcwb%2findex.html%23%2freceive-detail%3fid%3d79%26mobile%3d13703937803%26token%3d3d5fc44f824f4345a684a328571953bc%26govId%3d6447&areaKey=PY","url");
//        try {
//            String decode = URLDecoder.decode(url, "utf-8");
//            System.out.println(decode);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(url);
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

    public static String getParamByUrl(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[^#&]*(&{1})";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(url);
        if (m.find()) {
            String replace = m.group(0).split("=")[1].replace("&", "");
            try {
                String decode = URLDecoder.decode(replace, StandardCharsets.UTF_8.toString());
                return decode;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }

        } else {
            return null;
        }
    }
}
