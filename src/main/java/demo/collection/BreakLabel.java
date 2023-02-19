package demo.collection;

import java.util.ArrayList;
import java.util.List;

public class BreakLabel {
    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        strList.add("d");
        strList.add("e");
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);

       outer: for (int i = 0; i < intList.size(); i++) {
           System.out.println(intList.get(i));
            for (int t = 0; t < strList.size(); t++) {
                if (strList.get(t).equals("b")){
                    break outer;
                }
                System.out.println(strList.get(t));
            }
        }
    }
}
