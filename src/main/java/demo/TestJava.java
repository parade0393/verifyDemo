package demo;

import java.util.HashMap;

public class TestJava {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("LY", 4);
        Integer ly = map.get("LY");
        ly = null == ly ? 1 : ++ly;
        System.out.println(ly);
        System.out.println(ly % 5 == 0?"是":"否");
    }
}

