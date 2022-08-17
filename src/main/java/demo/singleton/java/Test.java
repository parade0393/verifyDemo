package demo.singleton.java;

public class Test {
    public static void main(String[] args) {
        InnerStaticClass instance = InnerStaticClass.getInstance();
        System.out.println(instance.getName());
        instance.setName("change");
        System.out.println(instance.getName());
    }
}
