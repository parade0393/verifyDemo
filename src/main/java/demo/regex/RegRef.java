package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则反向引用
 */
public class RegRef {
    public static void main(String[] args) {
//        String content = "df的话覅的1991返回发1234覆盖3333erd12321-333999111";
//        String regStr = "(\\d)(\\d)\\2\\1";//连续4位数，第1位和第四位相同，第二位和第三位相同
//        String regStr = "(\\d)\\1{3}";//连续4位数，都相同
        String regStr = "\\d{5}-(\\d)\\1{2}(\\d)\\2{2}(\\d)\\3{2}";//前面是一个5位数，然后是一个-号，然后是一个9位数，连续的每3位相同

        String content = "我....我要....学学学学....编程Java!";//结巴语句变成我哟啊学编程Java
        content = content.replaceAll("\\.", "").replaceAll("(.)\\1+","$1");
//        String $1 = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");//$1是在外部使用反向引用
//        System.out.println($1);
        System.out.println(content);//我要学编程java

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到了："+matcher.group(0));
        }
    }
}
