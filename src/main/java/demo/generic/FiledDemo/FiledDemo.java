package demo.generic.FiledDemo;


import demo.generic.demo.FiledType;

import java.lang.reflect.*;


public class FiledDemo {
    public static void main(String[] args) {
        Field[] declaredFields = FiledType.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Type type = declaredField.getGenericType();
            if (type instanceof ParameterizedType){//一个参数化类型对应一个原始类型，可能对应好多参数类型
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.println("FiledName:"+declaredField.getName()+"是ParameterizedType");
                Type[] arguments = parameterizedType.getActualTypeArguments();
                for (Type argument : arguments) {
                   if (argument instanceof WildcardType){
                        System.out.println(declaredField.getName()+"--的泛型参数"+argument.getTypeName()+"--是-WildcardType");
                    }else if (argument instanceof TypeVariable){
                        System.out.println(declaredField.getName()+"--的泛型参数"+argument.getTypeName()+"--是-TypeVariable");
                    }
                }
            }else if (type instanceof Class){
                System.out.println(declaredField.getName()+"是Class");
            }else if (type instanceof TypeVariable){
                System.out.println(declaredField.getName()+"是TypeVariable");
            }else if (type instanceof WildcardType){
                System.out.println(declaredField.getName()+"是WildcardType");
            }else if (type instanceof GenericArrayType){
                System.out.println(declaredField.getName()+"是GenericArrayType");
                GenericArrayType arrayType = (GenericArrayType) type;
                Type componentType = arrayType.getGenericComponentType();//返回组成泛型数组的实际参数化类型，如List[] 则返回 List
                if (componentType instanceof TypeVariable){
                    System.out.println("泛型数组的实际参数化类型:"+componentType+"是TypeVariable");
                }else if (componentType instanceof ParameterizedType){
                    System.out.println("泛型数组的实际参数化类型:"+componentType+"是ParameterizedType");
                }else if (componentType instanceof Class){
                    System.out.println("泛型数组的实际参数化类型:"+componentType+"是Class");//这个可能，如果原始类型是Class，那么这个属性的类型就不可能是GenericArrayType
                }
            }
        }
    }
}
