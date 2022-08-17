package demo.closure;

import java.util.concurrent.TimeUnit;

public class AnonymousInnerClass {
    private static User user;
    private static String key;

    public static void main(String[] args) {
        testMethod("parade");
        try {
            TimeUnit.MICROSECONDS.sleep(100);
            testMethod("0393");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testMethod(String word) {
        key = word;
        if (user == null) {
            user = new User();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MICROSECONDS.sleep(300);
                        System.out.println("内" + word);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println(user);
        System.out.println("外" + word);

    }


    static class User {

    }
}
