package demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListRemoveDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(10,20,30,40,50,60,70,80));
        //错误删除
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            System.out.println(integer);
            list.remove(i);//先把10删除了，这个时候20成了索引为0的那个，而下次循环的i值是1，对应的30，所以就把20隔过去了
            System.out.println("当前值:"+list.get(i)+",执行次数"+i);
        }
        System.out.println(list);//[20, 40, 60, 80]
        //正确删除一，使用for循环正序遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);//1,2,2,3,4,5,6
            if (list.get(i) == 30){
                list.remove(i);
                //删除后，删除的元素的索引变成了下一个元素的，所以这里修正一下
                //及如果这里i值是2，那么下一个还要重新从索引为2的元素进行判断，因为少了一个元素
                i = i - 1;
            }

        }
        System.out.println(list);
        //正确删除二，使用for循环倒序遍历
        for (int i = list.size()-1;i>=0;i--){
            Integer integer = list.get(i);
            System.out.println("i:"+i+"--current"+integer+"--size:"+(list.size()-1));
            list.remove(i);//刚开始list的最后一个索引是7第一次移除80，这个时候list的最后一个的索引变成了6，而i也变成了6
        }

        System.out.println(list);//[]
        //正确删除三，使用Iterator的remove()方法
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next() == 30){
                iterator.remove();//这里必须使用迭代器的方法
            }
        }
        System.out.println(list);
        //正确删除四
        list.removeIf(integer -> integer == 30);//jdk 1.8之后 ,集合是对象的时候，这种写法看卡里非常优雅
        System.out.println(list);
        //无论是哪种删除方式，内部都是用的迭代器的原里
    }
}
