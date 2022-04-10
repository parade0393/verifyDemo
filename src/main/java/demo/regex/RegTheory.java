package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分析java正则表达式底层实现
 */
public class RegTheory {
    public static void main(String[] args) {
        String content = "1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，Sun公司发布了" +
                "第二代Java平台（简称为Java2）的3个版本：J2ME（Java2 Micro Edition，Java2平台的微型" +
                "版），应用于移动、无线及有限资源的环境；J2SE（Java 2 Standard Edition，Java 2平台的" +
                "标准版），应用于桌面环境；J2EE（Java 2Enterprise Edition，Java 2平台的企业版），应" +
                "用3433于基于Java的应用服务器。Java 2平台的发布，是Java发展过程中最重要的一个" +
                "里程碑，标志着Java的应用开始普及9899";
        //目标匹配所有四个数字
        //1.\\d表示任意一个数字
//        String regStr = "\\d\\d\\d\\d";
        String regStr = "(\\d\\d)(\\d\\d)";
        //2.创建模式对象[即正则表达式对象]
        Pattern pattern = Pattern.compile(regStr);
        //3.创建匹配器matcher，按照正则表达式的规则，去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        //4.开始匹配
        /**
         * matcher.find完成的任务
         * 1.根据指定的规则，定位满足规则的子字符串(比如1998)
         * 2.找到后，将子字符串开始的索引记录到matcher对象的属性int[] groups;
         *     2.1 groups[0] = 0 , 把改子符串结束时的索引+1的值记录到groups[1] = 4
         *     2.2 分组情况下：记录1组()匹配的字符串 groups[2] = 0 groups[3] = 2
         *     2.3 分组情况下：记录2组()匹配的字符串 groups[4] = 2 groups[3] = 4
         *     2.4 如果有更多的分组
         * 3.同时记录oldLast的值为子符串结束时的索引+1的值即4，即下次执行find时，就从4开始匹配
         * matcher.group(0)分析
         * 1.根据groups[0] = 0 和groups[1] = 4的记录的位置，从content开始截取子字符串返回
         * 往下找同理
         * matcher.find完成的任务(考虑分组)
         * String regStr = "(\\d\\d)(\\d\\d)"; 第一个小括号是第一组  第二个小括号是第二组
         *
         */
        while (matcher.find()) {
            //小结：
            //1.如果正则表达式有()即分组
            //2.取出匹配的字符串规则如下
            //3.group(0)表示匹配到的子字符串
            //4.group(1)表示匹配到的子字符串中的第一组字符串
            //5.group(2)表示匹配到的子字符串中的第二组字符串
            //6....但是分组数不能越界
//            System.out.println("找到:"+matcher.group(0));
        }

        String str = "parade岁月山大佛i和房符合parade0393df发parade同学";
//        String regEStr = "parade(岁月|0393)";//这样写会捕获分组
        String regEStr = "parade(?:岁月|0393)";
//        String regEStr = "parade(?:岁月|0393|同学)";//parade岁月和parade0393和parade同学 不会捕获分组
//        String regEStr = "parade(岁月|0393)";//parade岁月和parade0393
//        String regEStr = "parade(?=岁月|0393)";//parade和parade 非捕获parade岁月的parade和parade0393的parade
//        String regEStr = "parade(?!岁月|0393)";//parade和parade 非捕获parade岁月的parade和parade0393的parade
        Pattern compile = Pattern.compile(regEStr);//parade后边不是岁月和0393的parade  和上面的是取反
        Matcher match = compile.matcher(str);
        while (match.find()) {
            System.out.println(match.group(0));
//            System.out.println(match.group(1));//使用非捕获分组，这里写就会报错IndexOutOfBoundsException
        }
    }
}
