package demo.sort;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        //正序排序，年龄为null时为小
        StudentAsc studentWang = new StudentAsc("王小二", 10);
        StudentAsc studentZhang = new StudentAsc("张三", 1);
        StudentAsc studentGou = new StudentAsc("狗子", 99);
        StudentAsc studentZhao = new StudentAsc("赵六", 40);
        StudentAsc studentLi = new StudentAsc("李四", null);
        List<StudentAsc> studentAscs = new ArrayList<StudentAsc>(Arrays.asList(studentWang, studentZhang, studentGou, studentZhao, studentLi));
//        Collections.sort(studentAscs);
//        System.out.println("自定义对象，升序排序：");

        Collections.sort(studentAscs, new Comparator<StudentAsc>() {
            @Override
            public int compare(StudentAsc o1, StudentAsc o2) {
                System.out.println(o1.getName()+"---"+o2.getName());
                if (null == o1.getAge()){
                    return -1;
                }
                if (null == o2.getAge()){
                    return 1;
                }
                System.out.println(studentAscs);
                return o1.getAge()-o2.getAge();
            }
        });


        for(StudentAsc studentAsc : studentAscs) {
            System.out.println(studentAsc.toString());
        }
    }
}
