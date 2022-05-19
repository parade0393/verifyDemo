package demo.innerClass;

/**
 * 匿名内部类是唯一一种没有构造方法的类
 * 匿名内部类的主要作用是继承其他类或者实现接口，并不需要增加额外的方法，方便对继承的方法进行实现或重写
 *  匿名内部类名称:
 *  包名.外部类名$由1开始的正整数-按照类装载顺序依次排列
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Thread thread = new Thread() {//匿名内部类1:$1
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        thread.start();
        Object o = new Object() {//匿名内部类2:$2
        };
    }
}
