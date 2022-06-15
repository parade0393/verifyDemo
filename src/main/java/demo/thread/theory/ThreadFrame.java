package demo.thread.theory;

/**
 * 栈帧是以线程为单位
 * 不同线程之间的栈内存是相互独立的
 * 演示的时候debug的断点的suspend要设置成thread模式
 */
public class ThreadFrame {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                method1(20);
            }
        };
        thread.setName("parade");
        thread.start();
        method1(10);
    }

    public static void method1(int x){
        int y = x + 1;
        Object o = method2();
        System.out.println(o);
    }

    public static  Object method2(){
        Object o = new Object();
        return 0;
    }
}
