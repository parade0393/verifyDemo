package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String regex = "20\\d\\d";
        System.out.println("2019".matches(regex));//true
        System.out.println("2100".matches(regex));//false
        boolean matches = Pattern.matches(regex, "2019");//这就是String类matches的源码
        System.out.println(matches);
        Pattern pattern = Pattern.compile("20\\d\\d");
        Matcher matcher = pattern.matcher("2019");
        while (matcher.find()){
            System.out.println(matcher.group());//2019
        }
    }
}
