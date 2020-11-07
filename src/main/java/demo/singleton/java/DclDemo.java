package demo.singleton.java;

/**
 * 双重检验
 */
public class DclDemo {

    private static volatile DclDemo dclDemo;

    private DclDemo() {
    }


    /**
     * 这种在某些jvm上是补安全的,主要原因在于jvm的“优化指令重排机制”以及dclDemo = new DclDemo()并非是原子性操作
     * dclDemo = new DclDemo()的过程如下
     * 1.在堆上开辟一块内存空间
     * 2.对对象进行设置，初始化
     * 3.返回对象引用到栈中
     * 而在JVM的优化指令重排可能会导致原本的1，2，3执行顺序变为1，3，2.此时当线程A执行了1,3后挂起，B线程调用了getInstance()，会判断dclDemo!=null
     * 从而将未初始化完的dclDemo返回，导致NPE
     * 解决：自JDK1.5之后的关键字volatile会给修饰的对象右两个特征：1.确保所有线程看到的这个变量的值是一致的2.禁止优化指令重排
     * 所以改成 private static volatile DclDemo dclDemo;即可
     */
    private static DclDemo getInstance() {
        if (dclDemo == null) {//只有在第一次创建的时候同步检验，提升了效率
            synchronized (DclDemo.class) {//防止多个线程进入第一个if中，重复创建对象
                if (dclDemo == null) {
                    dclDemo = new DclDemo();
                }
            }
        }

        return dclDemo;
    }
}
