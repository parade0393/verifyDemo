package demo.generic.feflect;

import demo.annotation.JsonFiled;

public class ReflectClass {
    private String nickName;
    public  int num = 3;
    public int add(int a,int b){
        return a + b;
    }
    public int sub(int a,int b){
        return a - b;
    }

    static {
        System.out.println("DemoClass静态代码块");
    }
    {
        System.out.println("DemoClass初始化代码块");
    }

    public ReflectClass(){
        System.out.println("无参构造");
    }

    public ReflectClass(String string){
        System.out.println("DemoClass构造方法");
    }

    public String getNickName() {
        return nickName;
    }

    @Deprecated
    public void setNickName(@JsonFiled String name,int max) {
        this.nickName = name;
    }

    public int getNum() {
        return num;
    }

    private void  testMethod(){
        System.out.println("静态方法");
    }
}
