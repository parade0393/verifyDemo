package demo.innerClass;

/**
 * 静态内部类和成员内部类类似，只是多了static关键字
 * 因此静态内部类是不能访问访问外部非静态成员的
 * 静态内部类名称和成员内部类一致
 */
public class StaticInnerClass {
    private String name;
    private static int age;
    static class InnerClass{
        public void test(){
            System.out.println(age);
        }
    }

    static class Inner{

    }
}
