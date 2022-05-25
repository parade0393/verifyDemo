package demo.generic.methodDemo;

import demo.generic.demo.ParameterMethod;

import java.lang.reflect.*;

public class ParameterDemo {
    public static void main(String[] args) {
        Method[] methods = ParameterMethod.class.getDeclaredMethods();
        for (Method method : methods) {
            Type[] types = method.getGenericParameterTypes();
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < types.length; i++) {
                Type type = types[i];
                if (type instanceof ParameterizedType){
                    System.out.println("参数:"+parameters[i]+"是ParameterizedType类型");
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] arguments = parameterizedType.getActualTypeArguments();
                    for (Type argument : arguments) {
                        if (argument instanceof WildcardType){
                            System.out.println("\t实参类型"+argument.getTypeName()+"是WildcardType");
                        }else if (argument instanceof TypeVariable){
                            System.out.println("\t实参类型"+argument.getTypeName()+"是TypeVariable");
                        }
                    }
                }else if (type instanceof GenericArrayType){
                    System.out.println("参数:"+parameters[i]+"是GenericArrayType类型");
                    GenericArrayType arrayType = (GenericArrayType) type;
                    Type componentType = arrayType.getGenericComponentType();
                    if (componentType instanceof ParameterizedType){
                        System.out.println("\t实参类型"+componentType.getTypeName()+"是ParameterizedType");
                        ParameterizedType parameterizedType = (ParameterizedType) componentType;
                        Type[] arguments = parameterizedType.getActualTypeArguments();
                        for (Type argument : arguments) {
                            if (argument instanceof TypeVariable){
                                System.out.println("\t实参类型"+argument.getTypeName()+"是TypeVariable");
                            }else if (argument instanceof WildcardType){
                                System.out.println("\t实参类型"+argument.getTypeName()+"是WildcardType");
                            }
                        }
                    }else if (componentType instanceof TypeVariable){
                        System.out.println("\t实参类型"+componentType.getTypeName()+"是TypeVariable");
                    }
                }
            }
        }
    }
}
