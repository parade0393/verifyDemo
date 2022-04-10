package demo.regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 正则修饰符
 */
public class RegModifier {
    public static void main(String[] args) {
        //大小写
        String content = "123sd#&^%SD";
        String regStr = "(?i)[a-z]+";//(?i)代表后边的忽略大小写不会计入分组 这种方式可以灵活的控制大小写
        regStr = "[a-z]+";
        Pattern pattern = Pattern.compile(regStr);
        pattern = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE);//这样代表整体忽略大小写
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {//如果只想取匹配到的第一个这里用if就行了
//            System.out.println("找到了："+matcher.group());
        }

        //多行匹配
        content =
                "            #1 js,90 #\n" +
                        "            #2 java,86 #\n" +
                        "            #3 djfi,93 # dsf \n" +
                        "            #4 Kotlin,87 #\n" +
                        "    ";
        regStr = "^\\s*#\\d+.+\\s+#$";
        pattern = Pattern.compile(regStr, Pattern.MULTILINE);//如果不用MULTILINE模式是不行的
        matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
      List<Map<String,String>> result =  list.stream().map(s -> {
            String[] array = s.replaceAll("\\s*#\\d+", "").replaceAll("#", "").replaceAll("\\s", "").split(",");
            HashMap<String,String> map =new HashMap<>();
            map.put("name", array[0]);
            map.put("price", array[1]);
            return map;
        }).collect(Collectors.toList());
        for (Map<String, String> map : result) {
            System.out.println(map.entrySet());
        }
    }
}
