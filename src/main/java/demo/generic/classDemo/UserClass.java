package demo.generic.classDemo;

import demo.generic.demo.MixinClass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ParameterizedType
 *
 * 父类BaseClass不是一个普通的class(Type)，而是一个ParameterizedType
 * 这个ParameterizedType的形参是<T,K extends Shape & Point & Food>，实参是<String,MixinClass>
 */
public class UserClass extends BaseClass<Integer, MixinClass>{
    public static void main(String[] args) {
        Type superclass = UserClass.class.getGenericSuperclass();
        if (superclass instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) superclass;
            System.out.println(type);//demo.generic.classDemo.BaseClass<java.lang.Integer, demo.generic.demo.MixinClass>
            Type[] arguments = type.getActualTypeArguments();//返回实参的类型
            for (Type argument : arguments) {
                System.out.println(argument);//java.lang.Integer和demo.generic.classDemo.MixinClass
            }
        }

        //有时候封装框架的时候需要用到这个知识点，有一个基类，其他的都继承他，根据继承的类型不同，做具体的操作
        Type type = UserClass.class.getGenericSuperclass();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class argument = (Class) parameterizedType.getActualTypeArguments()[0];
            System.out.println(argument == Integer.class);//true 判断实际类型是不是某个class
            System.out.println(Number.class.isAssignableFrom(argument));//true 判断实际类型是不是继承于某个类
        }
    }
}
