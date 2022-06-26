package demo.thread;

import java.util.ArrayList;

public class ThreadFieldLocalSafe {
    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                threadSafe.method1(200);
            },"Tread"+i+1).start();
        }
    }

}

class ThreadSafe{
    //局部变量多个线程在堆里就有多个对象，final防止被重写
    public final void method1(int loopNumber){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    public void method2(ArrayList<String> list){
        list.add("1");
    }
    public void method3(ArrayList<String> list){
        list.remove(0);
    }
}
