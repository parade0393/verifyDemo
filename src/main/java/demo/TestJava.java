package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava {
    public static void main(String[] args) {
        String text = "这是一个超链接：https://www.example.com/地防护多少分https://www.baidu.com 懂法守法";
//        text = "【洛阳市委办】 点击http://tinyurl.zwtgov.com/dB 查看附件";
//        text = "这事一个超链接http://pyapi.dongben.cc:9090/Html/oa/6447/5.2.3.61/cwb/index.html#/receive?govId=6447&token=e89593e7c1ef4facb364b7af08b98cee&mobile=13703937803&html_version=202108111145此功能的只有濮阳有";
//        text = "东方花都https://blog.csdn.net/DovSnier/article/details/103424638?status=edit&os=windows哈哈地方";
        String regex = "^(https?|ftp)://(-\\.)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        regex = "((http[s]{0,1})://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9#\\&%_\\./-~-]*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String url = matcher.group();
            System.out.println(start+"-----"+end+"-------"+url);
        }

    }
}

