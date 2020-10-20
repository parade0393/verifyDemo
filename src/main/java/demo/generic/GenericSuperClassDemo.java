package demo.generic;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取类的泛型参数类型
 */
public class GenericSuperClassDemo {

    @Test
    public void testGenericParameter(){
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        System.out.println("class::" + aClass);//class::class demo.generic.User
        Class<?> superclass = aClass.getSuperclass();
        System.out.println("super::"+superclass);//super::class java.util.ArrayList
        Type type = aClass.getGenericSuperclass();
        System.out.println(type);//java.util.ArrayList<java.lang.Integer>
        ParameterizedType parameterizedType = (ParameterizedType)type;////ParameterizedType参数化类型，即泛型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取类型中的泛型参数
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);//class java.lang.Integer
        }

    }
}
