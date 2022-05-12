package demo.thread;

public class Interrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    if (Thread.interrupted()){
                        //判断外界是否调用了interrupt方法
                        //如果外界调用了interrupt方法那么Thread.interrupted()方法会返回true同时下次调用的时候会返回false
                        //而isInterrupted()则永远都会返回true

                        //通常在耗时操作之前判断中断标记，并在这里释放资源
                        return;
                    }
                    try {
                        Thread.sleep(2000);//如果线程在sleep的时候外界调用了thread.interrupt()那么这里就会进入异常，需要在异常了处理中断逻辑
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //如果进入异常，说明也需要中断，处理逻辑和上面一样
                        //释放资源
                        return;
                    }
                    System.out.println("number:"+i);

                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();//这个只是标记一下线程是中断状态，即通知线程你需要被中断了，而中断标记由isInterrupted()和Thread.interrupted()静态方法判断
    }
}
