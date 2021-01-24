package demo.collection;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class List2StringDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //方法一，限制最少的方法，如果集合里是对象，那么使用这种方法也很方便
        StringBuilder builder = new StringBuilder();
        for (Integer integer : list) {
            builder.append(integer).append(",");//builder.append(demo.getId()).append(",");
        }
        if (!builder.toString().isEmpty()){
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());//1,2,3,4
        String[] split = builder.toString().split(",");//String转数组
        System.out.println(Arrays.toString(split));//先转string，再转数组
        //Arrays.asList(split) string先转数组，再转list


        //如果集合里元素比较简单，是string，那么用下面的方法更简单
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        String join = String.join(",", stringList);//jdk 8以后的方法
        System.out.println(join);//a,b,c,d

        String[] strings = stringList.toArray(new String[0]);//list直接转数组
        String join1 = String.join(",", strings);
        System.out.println(join1);//a,b,c,d
    }


}
