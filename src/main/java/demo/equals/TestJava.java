package demo.equals;

import java.util.Objects;

public class TestJava {
    public static void main(String[] args) {
        float a = 10.222222225f;
        float b = 10.222222229f;
        System.out.println(a<b);//false
        System.out.println(a==b);//true
        boolean parade = Objects.equals(null, "parade");
        System.out.println(parade);
    }
}
