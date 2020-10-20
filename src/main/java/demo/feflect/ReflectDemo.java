package demo.feflect;

import demo.feflect.DemoClass;

import java.lang.reflect.*;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> forName = Class.forName("demo.feflect.DemoClass");//class demo.feflect.ReflectDemo,只会调用静态代码块
        try {
            Field name = forName.getDeclaredField("nickName");
            DemoClass o = (DemoClass) forName.newInstance();//通过Class对象得到实例对象
            name.setAccessible(true);//将私有属性设置为可访问
            name.set(o,"parade");//给私有属性赋值
            System.out.println(o.getNickName());//验证赋值成功
            //getField只能获取非私有属性
            Field num = forName.getField("num");//非私有属性赋值
            num.setInt(o,5);
            System.out.println(o.getNum());//验证赋值成功
            Object o1 = num.get(o);//或者继续通过反射获取属性值
            System.out.println(o1);
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

}
