package demo.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型super和extends的使用
 */
public class GenericTest {

    @Test
    public void test(){
        List<Child> parentList = new ArrayList<Child>();
        List<? super Parent> parents = new ArrayList<GrandParent>();//代表泛型类型可以是Parent及其父类
        parents.add(new Child());//其实这里可以add，但是只可以添加parent及其子类
        parents.add(new Parent());//这里添加的两种类型都可以认为是parent，
        //get处理的返回值有可能是parent或者父类，而parent和其父类在使用上是有区别的，如果
        Object object = parents.get(0);//这个时候get返回值只能赋值给Object引用，而这样其没有什么实际意义，
    }

    public void test2(){
        List<? extends Number> foo1 = new ArrayList<Number>();
        List<? extends Number> foo2 = new ArrayList<Integer>();
        List<? extends Number> foo3 = new ArrayList<Double>();
        //使用? extends 声明的泛型，你得到的永远可以认为是一个Number，但是你不能执行写入因为，因为泛型有可能是Numner，Integer或者Double，而你不能向一个Integer集合里添加Double对象

    }

    public void test3(){
        List<? super Integer> foo = new ArrayList<Integer>();
        List<? super Integer> foo1 = new ArrayList<Number>();
        List<? super Integer> foo2 = new ArrayList<Object>();
        //你不能保证读取到Integer，因为有泛型类型可能是Object；写入操作，你可以写入Integer及其子类，因为上述集合都支持Integer，但是你不能插入Number或者，因为泛型可能是Integer

    }
}
