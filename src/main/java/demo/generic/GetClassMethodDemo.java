package demo.generic;

import demo.generic.demo.A;
import demo.generic.demo.B;
import demo.generic.demo.C;

public class GetClassMethodDemo {
    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        A c = new C();
        System.out.println(a.getClass());//class demo.generic.basic.demo.A
        System.out.println(b.getClass());//class demo.generic.basic.demo.B
        System.out.println(c.getClass());//class demo.generic.basic.demo.c
    }
}
