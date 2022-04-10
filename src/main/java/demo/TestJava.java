package demo;

import java.util.HashMap;
import java.util.stream.Stream;

public class TestJava {

    public static void main(String[] args) {
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue) // double 类型转 int
                .mapToObj(i -> {
                    HashMap map = new HashMap();
                    map.put("name","i"+i);
                    return map;
                }) // 对值拼接前缀 a
                .forEach(System.out::println); // for 循环打印

// a1
// a2
// a3

    }
}

