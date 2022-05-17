package demo.innerClass;

/**
 * 成员内部类
 * 可以看出内部类可以随心所欲的访问外部类的成员，包括外部类的私有成员
 * 外部类访问内部类的成员就没那么容易了
 * 1.外部类非静态方法访问，需要先创建一个内部类的对象，然后就可以随心所欲的访问了
 * 2.静态方法中访问，必须先创建外部类的对象，因为内部类是依附于外部类的
 * 局部内部类
 *
 */
public class FiledInnerClass {
    private String name = "parade";

    public static void test(){
//        new InnerClass().print();
        int a = 10;
        new FiledInnerClass().new InnerClass().print();
        new Runnable(){
            @Override
            public void run() {
                System.out.println(a);
            }
        };
    }

    public void visitInner(){
        new InnerClass().print();
        System.out.println(new InnerClass().score);
    }

    public void localTest(){
        String innerField = "test";
        //这里也不能访问LocalClass,外部想要访问局部类的方法，可以返回一个局部类的对象
        class LocalClass extends FiledInnerClass{
            private int level = 0;
            public void testLevel(){
                System.out.println(innerField);
            }
        }
    }

    public static void main(String[] args) {

    }

    class InnerClass{
        private String score = "88";
        private void print(){
            System.out.println(score);
            System.out.println(name);
        }
    }
}
