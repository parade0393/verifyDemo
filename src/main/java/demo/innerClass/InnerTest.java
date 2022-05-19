package demo.innerClass;

public class InnerTest {
    public static void main(String[] args) {
        FiledInnerClass.InnerClass innerClass = new FiledInnerClass().new InnerClass();
        System.out.println(innerClass.getClass());//demo.innerClass.FiledInnerClass$InnerClass
    }
}
