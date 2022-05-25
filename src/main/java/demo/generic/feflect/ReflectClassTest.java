package demo.generic.feflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ReflectClassTest {
    @Test
    public void test1() {
        //不会调用调用任何方法
        Class<?> demoClassClass = ReflectClass.class;
    }

    @Test
    public void test2() throws ClassNotFoundException {
        //只会调用静态方法
        Class<?> aClass = Class.forName("demo.generic.feflect.ReflectClass");
    }

    @Test
    public void Test3() {
        //静态代码块，动态代码块和构造方法都会调用
        Class<?> aClass = new ReflectClass().getClass();
    }

    @Test
    public void test4() throws ClassNotFoundException {
        Class<ReflectClass> demoClassClass = ReflectClass.class;
        System.out.println("-------------------");
        Class<?> aClass = Class.forName("demo.generic.feflect.ReflectClass");
        System.out.println("******************************");
        Class<? extends ReflectClass> aClass1 = new ReflectClass().getClass();
    }

    /**
     * 不同方式的实例对比
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void test5() throws ClassNotFoundException {
        Class<ReflectClass> clz1 = ReflectClass.class;
        Class<?> clz2 = Class.forName("demo.generic.feflect.ReflectClass");
        Class<? extends ReflectClass> clz3 = new ReflectClass("").getClass();
        System.out.println(clz1 == clz2);//true
        System.out.println(clz2 == clz3);//true
        System.out.println(clz1 == clz3);//true
    }

    /**
     * 创建实例操作
     */
    @Test
    public void test6() {
        //一个类的静态代码块只会执行一次
        try {
            Class<ReflectClass> demoClassClass = ReflectClass.class;
            ReflectClass reflectClass = demoClassClass.newInstance();
            System.out.println("----------------");
            System.out.println(reflectClass);
            System.out.println("----------------");
            Constructor<ReflectClass> constructor = demoClassClass.getConstructor(String.class);
            System.out.println("----------------");
            ReflectClass parade = constructor.newInstance("parade");
            System.out.println(parade);
            ReflectClass pa = constructor.newInstance("0393");
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
            Class<?> forName = Class.forName("demo.generic.feflect.ReflectClass");//class demo.generic.feflect.ReflectDemo,只会调用静态代码块
            Field name = forName.getDeclaredField("nickName");
            ReflectClass o = (ReflectClass) forName.newInstance();//通过Class对象得到实例对象
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
            Class<?> forName = Class.forName("demo.generic.feflect.ReflectClass");
            Method[] methods = forName.getMethods();//获取所有非private方法，包括父类的方法
            Method[] declaredMethods = forName.getDeclaredMethods();//获取本类的所有方法，不包括父类
            Object o = forName.newInstance();
            for (Method declaredMethod : declaredMethods) {
//                System.out.println(declaredMethod.getName());
            }
            /**
             * 获取某个方法，第一个参数是方法名称，后面的参数说是方法的形参类型
             */
            Method setNickName = forName.getDeclaredMethod("setNickName", String.class,int.class);
            /**
             * 反射调用方法，第一个参数是实例对象，后面的参数是方法的实参
             */
            Field nickName = forName.getDeclaredField("nickName");
            nickName.setAccessible(true);
            System.out.println("old:" + nickName.get(o));//null
            setNickName.invoke(o, "newValue",0);
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
            Class<?> forName = Class.forName("demo.generic.feflect.ReflectClass");
            Object instance = forName.newInstance();
            Method method = forName.getDeclaredMethod("setNickName", String.class, int.class);

            /**
             * 获取方法的每个参数的每个注解,一个参数可能有多个注解
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
}
