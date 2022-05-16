package demo.thread;

public class ObjectTest {
    public static void main(String[] args) {
        runWaitDemo();
    }

    static void runWaitDemo(){
        new WaitDemo().runTest();
    }
}
