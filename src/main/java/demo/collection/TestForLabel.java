package demo.collection;

import java.util.ArrayList;
import java.util.List;

public class TestForLabel {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        List<String> an = new ArrayList<>();
        an.add("H");
        an.add("I");
        an.add("J");
        an.add("K");

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("b")){
                for (int t = 0; t < 5; t++) {
                    if (t == 2){
                        for (int k = 0; k < an.size(); k++) {
                            if ("I".equals(an.get(k))){
                                break;
                            }
                            System.out.println(an.get(k));
                        }
                        break;
                    }
                    System.out.println(t);
                }
                break;
            }
            System.out.println(s);
        }
    }
}
