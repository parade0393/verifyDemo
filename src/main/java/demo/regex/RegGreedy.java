package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java默认是贪婪匹配的
 * 任何一个其他限制符 (*, +, ?, {n}, {n,}, {n,m}) 后面时，匹配模式是非贪婪的后边紧跟是非贪婪的
 * 非贪婪匹配是尽可能少的匹配
 *
 */
public class RegGreedy {
    public static void main(String[] args) {
        String content = "123000";
        String regStr = "(\\d+?)(0*)";//如果"(\\d+)(0*)"，那么第一组是123000，第二组是""空字符串
        content = "hello234okdfsdf34ok";
        regStr = "\\d+?ok";//此时"\\d+?ok"效果一样
        regStr = "\\d+";//此时匹配 234和34
        regStr = "\\d+?";//此时匹配 2 3 4 3 4
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
//        if (matcher.matches()) {//matches是整体匹配
//            System.out.println("整体找到："+matcher.group());
//            System.out.println("group1:"+matcher.group(1));
//            System.out.println("group2:"+matcher.group(2));
//        }
        while (matcher.find()) {
            System.out.println("找到了："+matcher.group());
        }
    }

}
