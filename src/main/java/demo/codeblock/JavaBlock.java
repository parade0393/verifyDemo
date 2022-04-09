package demo.codeblock;


import demo.TestJava;

/**
 * parent static
 * 1111111111
 * parent static
 * son static
 * parent normal
 * son normal
 * constructor
 */
public class JavaBlock {
    static {
        System.out.println("parent static");
    }
    {
        System.out.println("parent normal");
    }
    public static void main(String[] args) {
        System.out.println("1111111111");
        new So();
    }
}

class So extends TestJava {
    {
        System.out.println("son normal");
        order = 4;
    }//代码块和属性是同级别的，就好像两个属性
    int order = 3;
    static {
        System.out.println("son static");
    }

    So(){
        System.out.println("constructor");
    }
}
