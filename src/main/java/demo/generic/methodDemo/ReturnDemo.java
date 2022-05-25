package demo.generic.methodDemo;

import demo.generic.demo.ReturnMethod;

import java.lang.reflect.*;

/**
 * 方法返回值泛型Demo
 */
public class ReturnDemo {
    public static void main(String[] args) {
        Method[] declaredMethods = ReturnMethod.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Type returnType = method.getGenericReturnType();
            if (returnType instanceof ParameterizedType){
                System.out.println(method.getName()+"--是--ParameterizedType--"+returnType);
            }else if (returnType instanceof TypeVariable){
                System.out.println(method.getName()+"返回值是:TypeVariable--"+returnType);
            }else if (returnType instanceof WildcardType){
                System.out.println(method.getName()+"返回值是:WildcardType--"+returnType);
            }else if (returnType instanceof Class){
                System.out.println(method.getName()+"返回值是:Class--"+returnType);
            }

        }
    }
}
