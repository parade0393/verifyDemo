package demo.feflect;

import demo.feflect.DemoClass;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class GetClassTest {
    @Test
    public void test1() {
        //不会调用调用任何方法
        Class<?> demoClassClass = DemoClass.class;
    }

    @Test
    public void test2() throws ClassNotFoundException {
        //只会调用静态方法
        Class<?> aClass = Class.forName("demo.feflect.DemoClass");
    }

    @Test
    public void Test3() {
        //静态代码块，动态代码块和构造方法都会调用
        Class<?> aClass = new DemoClass().getClass();
    }

    @Test
    public void test4() throws ClassNotFoundException {
        Class<DemoClass> demoClassClass = DemoClass.class;
        System.out.println("-------------------");
        Class<?> aClass = Class.forName("demo.feflect.DemoClass");
        System.out.println("******************************");
        Class<? extends DemoClass> aClass1 = new DemoClass().getClass();
    }

    /**
     * 不同方式的实例对比
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void test5() throws ClassNotFoundException {
        Class<DemoClass> clz1 = DemoClass.class;
        Class<?> clz2 = Class.forName("demo.feflect.DemoClass");
        Class<? extends DemoClass> clz3 = new DemoClass("").getClass();
        System.out.println(clz1 == clz2);
        System.out.println(clz2 == clz3);
        System.out.println(clz1 == clz3);
    }

    /**
     * 创建实例操作
     */
    @Test
    public void test6() {
        //一个类的静态代码块只会执行一次
        try {
            Class<DemoClass> demoClassClass = DemoClass.class;
            DemoClass demoClass = demoClassClass.newInstance();
            System.out.println("----------------");
            System.out.println(demoClass);
            System.out.println("----------------");
            Constructor<DemoClass> constructor = demoClassClass.getConstructor(String.class);
            System.out.println("----------------");
            DemoClass parade = constructor.newInstance("parade");
            System.out.println(parade);
            DemoClass pa = constructor.newInstance("0393");
            System.out.println(pa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射属性操作
     */
    @Test
    public void test7() {

        try {
            Class<?> forName = Class.forName("demo.feflect.DemoClass");//class demo.feflect.ReflectDemo,只会调用静态代码块
            Field name = forName.getDeclaredField("nickName");
            DemoClass o = (DemoClass) forName.newInstance();//通过Class对象得到实例对象
            name.setAccessible(true);//将私有属性设置为可访问
            name.set(o, "parade");//给私有属性赋值
            System.out.println(o.getNickName());//验证赋值成功
            //getField只能获取非私有属性
            Field num = forName.getField("num");//非私有属性赋值
            num.setInt(o, 5);
            System.out.println(o.getNum());//验证赋值成功
            Object o1 = num.get(o);//或者继续通过反射获取属性值
            System.out.println(o1);
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射方法操作，方法获取值和设置值
     */
    @Test
    public void test8() {
        try {
            Class<?> forName = Class.forName("demo.feflect.DemoClass");
            Method[] methods = forName.getMethods();//获取所有非private方法，包括父类的方法
            Method[] declaredMethods = forName.getDeclaredMethods();//获取本类的所有方法，不包括父类
            Object o = forName.newInstance();
            for (Method declaredMethod : declaredMethods) {
//                System.out.println(declaredMethod.getName());
            }
            /**
             * 获取某个方法，第一个参数是方法名称，后面的参数说是方法的形参类型
             */
            Method setNickName = forName.getDeclaredMethod("setNickName", String.class);
            /**
             * 反射调用方法，第一个参数是实例对象，后面的参数是方法的实参
             */
            Field nickName = forName.getDeclaredField("nickName");
            nickName.setAccessible(true);
            System.out.println("old:" + nickName.get(o));//null
            setNickName.invoke(o, "newValue");
            System.out.println("new:" + nickName.get(o));//newValue

            Method testMethod = forName.getDeclaredMethod("testMethod");
            testMethod.setAccessible(true);//调用私有方法同样要设置可见
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取方法的参数类型，参数的注解，方法的注解  方法的返回值类型
     */
    @Test
    public void test9() {
        try {
            Class<?> forName = Class.forName("demo.feflect.DemoClass");
            Object instance = forName.newInstance();
            Method method = forName.getDeclaredMethod("setNickName", String.class, int.class);

            /**
             * 获取方法的每个参数的每个注解
             */
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                for (Annotation annotation : parameterAnnotation) {
                    System.out.println("---");
                    System.out.println(annotation.annotationType());//获取注解的类型
                }
            }
            System.out.println("&&&&&&&&&&&&&&&&&&&&");
            Annotation[] annotations = method.getAnnotations();//用在方法上的注解
            for (Annotation annotation : annotations) {
                System.out.println(annotation.annotationType());
            }
            System.out.println("**********");
            Type[] genericParameterTypes = method.getGenericParameterTypes();//获取参数的类型
            for (Type genericParameterType : genericParameterTypes) {
                System.out.println(genericParameterType.getTypeName());//获取参数的类型java.lang.String或int
            }

            Type genericReturnType = method.getGenericReturnType();//获取方法返回值类型
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ParameterizedType、TypeVariable、WildcardType的测试
     * Class：原始类型，也叫RawType，不仅包括我们平常指的类，枚举，数组，注解，还包括基本数据类型int，float，List等,大类型
     * ParameterizedType:参数化类型，，大类型
     * TypeVariable:类型变量，比如List<T>中的T，，小类型
     * WildCardType：通配符类型，例如List<? extends Number> 里的? extends Number这种，小类型
     * GenericArrayType：数组类型，并不是我们平常说的String[],byte[](这种都属于Class)，是带有泛型的数组，比如T[],大类型
     */
    @Test
    public void test10() {
        try {
            Method[] declaredMethods = TypeTest.class.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
                for (Type genericParameterType : genericParameterTypes) {
                    if (genericParameterType instanceof ParameterizedType) {
                        ParameterizedType pa = (ParameterizedType) genericParameterType;
                        System.out.println("ParameterizedType:" + pa);
                        Type[] actualTypeArguments = pa.getActualTypeArguments();
                        for (Type actualTypeArgument : actualTypeArguments) {
                            if (actualTypeArgument instanceof WildcardType) {
                                System.out.println("genericParameterType--WildcardType:" + actualTypeArgument);
                                System.out.println("--WildcardType" + Arrays.asList(((WildcardType) actualTypeArgument).getUpperBounds()));
                            }else if (actualTypeArgument instanceof TypeVariable){
                                TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                                System.out.println("--typeVariable:"+typeVariable);
                            }else {
                                System.out.println("--class:"+actualTypeArgument);
                            }
                        }
                    } else if (genericParameterType instanceof WildcardType) {
                        WildcardType wildcardType = (WildcardType) genericParameterType;
                        System.out.println("WildcardType:" + wildcardType);
                    } else if (genericParameterType instanceof TypeVariable) {
                        TypeVariable typeVariable = (TypeVariable) genericParameterType;
                        System.out.println("TypeVariable:" + typeVariable);
                    } else if (genericParameterType instanceof GenericArrayType) {
                        GenericArrayType arrayType = (GenericArrayType) genericParameterType;
                        System.out.println("GenericArrayType:" + arrayType);
                        Type genericComponentType = arrayType.getGenericComponentType();
                        System.out.println("genericComponentType:" + genericComponentType);//到数组的实际参数类型
                        if (genericComponentType instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) genericComponentType;
                            //genericComponentType--ParameterizedType:demo.feflect.TypeTest<T>
                            System.out.println("genericComponentType--ParameterizedType:" + genericComponentType);
                            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                            for (Type actualTypeArgument : actualTypeArguments) {
                                if (actualTypeArgument instanceof TypeVariable) {
                                    TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                                    //--ParameterizedType--TypeVariable:T
                                    System.out.println("--ParameterizedType--TypeVariable:" + typeVariable);
                                }
                            }
                        }
                    } else {
                        Class clazz = (Class) genericParameterType;
                        System.out.println("Class:" + clazz);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 验证参数化类型
     * getActualTypeArguments 参数化类型的参数类型
     * getRawType 参数化类型的类型
     * 获取所有者类型（只有内部类才有所有者，比如Map.Entry他的所有者就是Map），若不是内部类，此处返回null
     */
    @Test
    public void test11() {
        try {
            Field[] declaredFields = ParameterizedTypeTest.class.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) declaredField.getGenericType();
                    System.out.println(declaredField.getName() + ":");
                    System.out.println("\t actualTypeArguments:" + Arrays.asList(parameterizedType.getActualTypeArguments()));
                    System.out.println("\t rawType:" + parameterizedType.getRawType());
                    System.out.println("\t ownerType:" + parameterizedType.getOwnerType());
                } else {
                    System.out.println(declaredField.getName() + ": is not parameterizedType");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试WildCard
     */
    @Test
    public void test12() {
        try {
            Field[] declaredFields = WildcardTypeTest.class.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Type genericType = declaredField.getGenericType();
                System.out.println("开始-----当前file：" + declaredField.getName() + "----------");
                if (genericType instanceof ParameterizedType) {
                    System.out.println("\t ParameterizedType:" + genericType);
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        System.out.println("\t获取到actualTypeArgument:"+actualTypeArgument);
                        if (actualTypeArgument instanceof WildcardType){
                            WildcardType wildcardType = (WildcardType) actualTypeArgument;
                            printWildCardType(wildcardType);
                        }else if (actualTypeArgument instanceof TypeVariable){
                            TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                            System.out.println("\t\tTypeVariable:"+typeVariable);
                        }
                    }
                }else if (genericType instanceof GenericArrayType){
                    GenericArrayType arrayType = (GenericArrayType) genericType;
                    System.out.println("\tGenericArrayType:"+arrayType);
                    Type genericComponentType = arrayType.getGenericComponentType();
                    if (genericComponentType instanceof WildcardType){
                        printWildCardType((WildcardType) genericComponentType);
                    }
                }else if (genericType instanceof TypeVariable){
                    TypeVariable typeVariable = (TypeVariable) genericType;
                    System.out.println("\ttypeVariable:"+typeVariable);
                }else {
                    System.out.println("\t type:"+genericType);
                    if (genericType instanceof WildcardType){
                        printWildCardType((WildcardType) genericType);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printWildCardType(WildcardType wildcardType) {
        for (Type upperBound : wildcardType.getUpperBounds()) {
            System.out.println("\t\t上界:" + upperBound);
        }
        for (Type lowerBound : wildcardType.getLowerBounds()) {
            System.out.println("\t\t下界:" + lowerBound);
        }
    }

}
