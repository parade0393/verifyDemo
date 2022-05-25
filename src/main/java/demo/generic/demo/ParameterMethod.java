package demo.generic.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 方法参数Demo
 */
public class ParameterMethod<T> {

    /**
     * @param value 本身是GenericArrayType，但是实参T是TypeVariable
     * @param wildArray 本身是GenericArrayType，但实参是ParameterizedType，他的实参是WildcardType
     */
    public void test(Map<Point, Shape> map,
                     Set<String> set1,
                     Class<?> clz,
                     List<String> list,
                     ArrayList<String> arrayList,
                     Map.Entry<String, String> entry,
                     List<? extends Number> wildCardList,
                     String str,
                     Integer i,
                     Set set,
                     List aList,
                     T name,
                     T[] value,
                     List<String>[] listArray,
                     List<? extends Number>[] wildArray,
                     int[] intArray,
                     String[] strArray) {

    }
}
