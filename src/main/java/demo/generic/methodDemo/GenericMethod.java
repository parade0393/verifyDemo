package demo.generic.methodDemo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 泛型方法和类型擦除
 * 在调用泛型方法时，可以指定泛型，也可以不指定泛型:
 * 在不指定泛型的情况下，泛型变量的类型为该方法中的几种类型的同一父类的最小级，直到Object
 * 在指定泛型的情况下，该方法的几种类型必须是该泛型的实例的类型或者其子类
 *
 * 其实在泛型类中，不指定泛型的时候，也差不多，只不过这个时候的泛型为Object，就比如ArrayList中，如果不指定泛型，那么这个ArrayList可以存储任意的对象。
 *
 * Java编译器是通过先检查代码中泛型的类型，然后在进行类型擦除，再进行编译。
 */
public class GenericMethod {
    /**
     * 泛型方法
     * @param <T> 声明一个泛型T，定义泛型方法时，必须加上这个泛型声明
     * @param t 参数为泛型类型
     * @return 返回值也是泛型类型
     */
    public <T> T getSubs(T t){
        return t;
    }

    public static void main(String[] args) {
        Integer add = GenericMethod.add(1, 2);//不指定泛型，这两个参数都是Integer,所以T为Integer
        Number add1 = GenericMethod.add(1, 1.2);//不指定泛型，参数类型一个是Integer，一个是Float，取同一父类最小级，所以是Number
        Serializable test = GenericMethod.add(1, "test");//不知道泛型，参数类型一个是Integer，一个是String，取同一父类最小级，所以是Object

        Integer add2 = GenericMethod.<Integer>add(1, 1);//指定了泛型类型Integer，所以只能为Integer极其子类
//        GenericMethod.<Integer>add(1,1.2)//编辑错误，指定了Integer，不能为Float
        Number add3 = GenericMethod.<Number>add(1, 1.2);//指定为Number，所以可以为Integer和Float


        ArrayList<String> list = new ArrayList<>();
        list.add("p");
//        list.add(1);//编辑不通过

        //类型检查就是针对引用的，谁是一个引用(=左边的)，用这个引用调用泛型方法，就会对这个引用调用的方法进行类型检测，而无关它真正引用的对象(=后边的)
        ArrayList list1 = new ArrayList<String>();
        list1.add("1");//编译通过
        list1.add(1);//编译通过

    }

    public static <T> T add(T x,T y){
        return y;
    }
}
