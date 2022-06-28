package demo;

public class TestJava {
    public static void main(String[] args) {
        final int[] count = {0};
        int i = count[0];
        count[0] = ++i;
        System.out.println(count[0]);
    }
}

