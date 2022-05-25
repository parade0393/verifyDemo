package demo.generic.classDemo;

import demo.generic.demo.Food;
import demo.generic.demo.Point;
import demo.generic.demo.Shape;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * TypeVariable
 * 范型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息。(通俗的来说，TypeVariable就是我们常用的T，K这种泛型变量)
 *
 * Java泛型编程的边界可以是多个，使用如<T extends A & B & C>语法来声明，其中只能有一个是类，并且只能是extends后面的第一个为类，
 * 其他的均只能为接口(和类/接口中的extends意义不同)。
 * 这里不能写成BaseClass<T,? extends Number>
 *
 * getBounds:返回当前类型的上边界，如果没有指定上边界，则默认为Object
 */
public class BaseClass<T,K extends Shape & Point & Food> {
    public static void main(String[] args) {
        TypeVariable<Class<BaseClass>>[] types = BaseClass.class.getTypeParameters();
        for (TypeVariable<Class<BaseClass>> type : types) {
//            System.out.println(type);//T,K
            Type[] bounds = type.getBounds();//getBounds之所以返回数据，是因为类型可以有多个限制
            for (Type bound : bounds) {
                System.out.println(bound);
            }
        }
        TypeVariable<Class<InnerClass>>[] typeParameters = InnerClass.class.getTypeParameters();//显然InnerClass不是泛型类，所以也就没有TypeVariable
        for (TypeVariable<Class<InnerClass>> typeParameter : typeParameters) {
            System.out.println(typeParameter);
        }
    }

    static class InnerClass{

    }
}
