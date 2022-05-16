package demo.thread;

/**
 * notifyAll 和 wait 是需要包在synchronized代码块里的
 */
public class WaitDemo {
    private String sharedString;
    private synchronized void initString(){
        sharedString = "parade";
        notifyAll();//这里一般使用notifyAll
    }
    private synchronized void printString(){
        while (sharedString == null){//这里必须使用while不能使用if，因为被唤醒的可能性非常多
            try {
                wait();//等待被唤醒同时把资源释放
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //被唤醒后从这里开始执行
        }
        System.out.println("string:"+sharedString);
    }

    public void runTest(){
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printString();
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
//                thread1.join(); 这里join的作用是等thread1执行外完毕在往下执行相当于是wait然后thead1执行完后自动notify
                // Thread.yield()//暂时先把自己的时间片交出去给同优先级的其他线程
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initString();
        });
        thread2.start();
    }
}
