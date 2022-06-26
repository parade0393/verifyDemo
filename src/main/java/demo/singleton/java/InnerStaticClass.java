package demo.singleton.java;

/**
 * 静态内部类单例,其实也是懒汉模式的一种，其线程安全，没有性能缺陷，且不依赖jdk版本
 * 静态内部来只有在getInstance时才会被加载，从而实例化instance，而且只加载一次
 * 类加载是懒惰的，只有在用到的时候才会去加载
 */
public class InnerStaticClass {

    private InnerStaticClass(){
        System.out.println("构造函数");
    }

    private static class InnerStaticClassHolder {
        private static final InnerStaticClass instance = new InnerStaticClass();
    }

    public static InnerStaticClass getInstance() {
        return InnerStaticClassHolder.instance;
    }

}
