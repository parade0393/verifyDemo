package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        //因为正则表达式可以用字符串来描述规则，所以都可以使用变量
        String variable = "\\d";
        String regex = "20\\d"+variable;//这相当于正则字面量的形式
        System.out.println("2019".matches(regex));//true
        System.out.println("2100".matches(regex));//false
        boolean matches = Pattern.matches(regex, "2019");//这就是String类matches的源码
        System.out.println(matches);
        Pattern pattern = Pattern.compile("20\\d\\d");//这相当于正则对象
        Matcher matcher = pattern.matcher("2019");
        while (matcher.find()){
            System.out.println(matcher.group());//2019
        }

        String content = "010-9999999";//false
        content = "020-9999999";//true
        content = "020-9999";//false
        content = "010";//true
        content = "020";//false
        System.out.println(content.matches("010|020-\\d{7,8}"));
        //这样写的意思是010或者020-\d{7,8}
        //即选择符右边的是一组，并且matches是整体匹配
    }
}
