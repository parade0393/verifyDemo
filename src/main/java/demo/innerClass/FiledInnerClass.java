package demo.innerClass;

/**
 * 那就是内部类是依附于外围类而存在的，其实也就是内部类存在着指向外围类的引用。
 * 内部类和外围类的联系是通过内部类所持有的外部类的引用来实现的，想要获取这个引用，可以使用外围类的.this来实现
 * 成员内部类
 * 可以看出内部类可以随心所欲的访问外部类的成员，包括外部类的私有成员
 * 外部类访问内部类的成员就没那么容易了
 * 1.外部类非静态方法访问，需要先创建一个内部类的对象，然后就可以随心所欲的访问了
 * 2.静态方法中访问，必须先创建外部类的对象，因为内部类是依附于外部类的
 * 成员内部类名称:
 * 无论成员内部类是否为静态，其全限定名都使  用包名.外部类名$内部类名
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


    public static void main(String[] args) {

    }

    class InnerClass{
        private String score = "88";
        private void print(){
            System.out.println(score);
            System.out.println(name);
        }
        public FiledInnerClass getOuter(){
            return FiledInnerClass.this;
        }
    }
}
