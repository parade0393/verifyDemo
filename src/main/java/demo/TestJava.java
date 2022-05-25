package demo;

import java.util.ArrayList;
import java.util.List;

public class TestJava {
    public static void main(String[] args) {
//        List<String>[] lists = new ArrayList<String>[10]; //编译不通过
        List<String> list = new ArrayList<>();
        Object o = list;
        List<Object> list1 = (ArrayList<Object>)o;
        list1.add(1);
        String s = list.get(0);
        System.out.println(s);//ClassCastException
    }
}

