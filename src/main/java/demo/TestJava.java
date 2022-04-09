package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava {

    public static void main(String[] args) {
        String content = "aAD356y";
        String regStr = "(?i)[a-z]+";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到了："+matcher.group(0));
        }
    }
}

