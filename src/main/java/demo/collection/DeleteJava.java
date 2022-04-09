package demo.collection;

import java.util.ArrayList;
import java.util.List;

public class DeleteJava {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("4");
        list.add("3");
        list.add("2");
        list.add("1");
        //方法一：
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if (Integer.parseInt(list.get(i)) > 2){
//                list.remove(i);
//            }
//        }
        //方法二
        list.removeIf(s -> Integer.parseInt(s) > 2);
        //方法三
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            if (Integer.parseInt(iterator.next()) > 2){
//                iterator.remove();
//            }
//        }
        System.out.print(list);
    }
}
