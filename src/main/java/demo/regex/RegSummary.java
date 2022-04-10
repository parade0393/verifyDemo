package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String类的方法
 * 1.split(String regex) 注意如果开头就匹配，那最终数组第一个元素是空字符
 * 2.replaceAll(String regx)
 * 3.replaceFirst(String regx)
 * 4.matches(String regx)//注意此方法是整体匹配  部分匹配请使用Matcher 类的find方法
 * Matcher类的方法
 * 1.find
 * 2.matches 和String类的matches效果一样
 * 3.lookAt() 从头开始找 非整体，找不到就返回false即有子字符串符合
 * 4.replaceAll 和replaceFirst和String类的效果一样
 * 5.appendReplacement 和 appendTail 结合 find可以在循环查找的时候定义每个匹配到的字符串的替换规则
 */
public class RegSummary {
    public static void main(String[] args) {
        String content = "1parade岁月山大佛i和房符合parade0393df发parade同学";
        String regStr = "parade(0393|岁月)";
        String[] split = content.split(regStr);
        //注意如果开头就匹配，那最终数组第一个元素是空字符
//        System.out.println(split.length);//3
        for (String s : split) {
//            System.out.println(s);//山大佛i和房符合  和  df发parade同学 其实数组有3个元素，第一个是空字符
        }
        //如果limit参数为 0 或 负数，则split()返回包含所有子字符串的数组。
        //如果limit参数为正（例如n），则split()返回数组的最大长度。
        String[] strings = content.split(regStr, 2);
        for (String a : strings) {
//            System.out.println(a);
        }
//        System.out.println(content.replaceAll(regStr,""));//山大佛i和房符合df发parade同学
//        System.out.println(content.replaceFirst(regStr,""));//山大佛i和房符合parade0393df发parade同学
//        System.out.println(content.matches(regStr));//false 因为matches是整体匹配


        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
//        System.out.println(matcher.replaceAll(""));
        while (matcher.find()) {
//            System.out.println("找到了："+matcher.group(0));
        }

        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("java Java JAVA JAva I love Java and you ?");
        StringBuffer sb = new StringBuffer();
        int index = 1;
        while(m.find()){
            //m.appendReplacement(sb, (index++ & 1) == 0 ? "java" : "JAVA"); 较为简洁的写法
            if((index & 1) == 0){//偶数
                m.appendReplacement(sb, "java");
            }else{
                m.appendReplacement(sb, "JAVA");
            }
            index++;
        }
        m.appendTail(sb);//把剩余的字符串加入
//        System.out.println(sb);

        String text = "abc123def";
        String regG = "(\\d+)";
        Pattern compile = Pattern.compile(regG);
        Matcher matcher1 = compile.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while (matcher1.find()) {
            matcher1.appendReplacement(buffer,"hh"+matcher1.group(0)+"hh");
//            System.out.println(matcher1.group(1));
        }
        matcher1.appendTail(buffer);
        System.out.println(buffer);
    }
}
