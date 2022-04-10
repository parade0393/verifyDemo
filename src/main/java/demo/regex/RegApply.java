package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式应用示例
 * [] 里的字符都是代表普通字符
 * [.()]就是普通的.和()
 */
public class RegApply {
    public static void main(String[] args) {
        //1.验证汉字
//        String content = "http://www.baidu.com?name=12.";
//        String regStr = "^[\u0391-\uFFE5]+$";//不写+代表有且仅有一个
        //2.验证邮政编码：要求1-9开头的一个六位数，比如：123890
//        String regStr = "^[1-9]\\d{5}$";
        //3.QQ号 要求：1-9开头的一个(5位数-10位数)
//        String regStr = "^[1-9]\\d{4,9}$";
        //4.手机 13 14 15 18开头的11位数
//        String regStr = "^1[3458]\\d{9}$";
        //5.url  .只有在[]里才代表.其他地方都需要转移\\.
//        String regStr = "^https?://([\\w-]+\\.)+[\\w-]+[\\w-?=#&/%.]*$";//[.]表示匹配.本身
        //5.验证是不是整数或者小数，要考虑正数和负数 123 -345 -34.5 0.45等
        String content = "0.1";
        String regStr = "^(-+)?([1-9]\\d*|0)(\\.\\d+)?$";
        //对一个URL解析；http://www.baidu.com:9090/abc/index.html
        //1.要求得到协议是 http;域名是 www.baidu.com;端口是:9090;文件名是index.html
       content = "http://www.baidu.com:9090/abc/index.html";
       regStr = "^(https?)://(?<yuming>(?i)[a-z.]+(?i)[a-z]):(\\d+)[\\w-/]*/([\\w.]+)$";//?<name>给分组命名
//       regStr = "^((?i)[a-z]+)://([a-zA-z.]+):(\\d+)[\\w-/]*/([\\w.]+)$";//(?i)代表后边的忽略大小写不会计入分组

        System.out.println(content.matches(regStr));
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            System.out.println("整体找到了："+matcher.group());
//            System.out.println("找到了协议："+matcher.group(1));
//            System.out.println("找到了域名："+matcher.group("yuming"));
//            System.out.println("找到了端口："+matcher.group(3));
//            System.out.println("找到了文件："+matcher.group(4));
        }
    }
}
