package demo.singleton.java;

/**
 * 饿汉式单例(对象在类加载时就被初始化，  即使没有调用过getInstance方法，因此即使不加锁也是线程安全的)
 * 缺点：当创建对象时需要依赖其它参数就无法使用此种模式了(在创建sHungryDemo对象前需要调用某个方法对其设置参数)
 *
 *
 *
 * 单例模式3要素
 * 1.私有的构造方法
 * 2.指向自己实例的私有静态引用
 * 3.以自己实例为返回值的静态的共有方法
 */
public class HungryDemo {


    private static final HungryDemo sHungryDemo = new HungryDemo();

    public static HungryDemo getInstance(){
        return sHungryDemo;
    }

    private HungryDemo(){

    }

    private String name;
}
