package demo.singleton.java;

/**
 * 懒汉式单例
 */
public class LazyDemo {

    private static LazyDemo lazyDemo;

   /* public static LazyDemo getInstance(){
        if (lazyDemo == null){
            //线程不安全，多线程的情况下会创建多个实例
            lazyDemo = new LazyDemo();
        }
        return lazyDemo;
    }*/

    public static synchronized LazyDemo getInstance(){
        if (lazyDemo == null){
            //线程安全，但是效率低，每次调用都会同步调用(其它线程需要等待)
            lazyDemo = new LazyDemo();
        }
        return lazyDemo;
    }

    private LazyDemo(){}

    private String name;


}
