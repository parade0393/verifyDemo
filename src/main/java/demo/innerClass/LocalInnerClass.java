package demo.innerClass;

/**
 * 局部内部类：
 * 局部内部类是定义在一个方法里或者一个作用域里的类，所以局部类的声明周期仅限于声明周期内，所以外部类是无法访问局部内部类的
 * 局部内部类好像一个变量一样，是不能被权限修饰修饰的
 * 局部内部类名称:外部类$由1开始的正整数+内部类名称--其中数字是局部类在外部类上下文出现的先后顺序(内部类名称相同的化数字才会自增，否则永远是1)
 */
public class LocalInnerClass {
    public void localTest(){
        String innerField = "test";
        //这里也不能访问LocalClass,外部想要访问局部类的方法，可以返回一个局部类的对象
        class LocalClass extends FiledInnerClass{
            private int level = 0;
            public void testLevel(){
                System.out.println(innerField);
            }
        }
        class Inner{}
    }

    public void func(){
        class InnerF{}
    }
}
