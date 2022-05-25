package demo.generic.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FiledType<T> {
    private Map<String, FiledType> map;//ParameterizedType
    private Set<String> set1;//ParameterizedType
    private Class<?> clz;//ParameterizedType,?是WildcardType
    private List<T> paList;//ParameterizedType,T是TypeVariable
    private Holder<String> holder;//ParameterizedType
    private List<String> list;//ParameterizedType
    private ArrayList<String> arrayList;//ParameterizedType
    private Map.Entry<String, String> entry;//ParameterizedType
    private List<? extends Number> wildCardList;//ParameterizedType，，? extends java.lang.Number是WildcardType

    private String str;//Class
    private Integer i;//Class
    private Set set;//Class
    private List aList;//Class

    private T name;//TypeVariable

    private T[] value;//GenericArrayType,T是GenericArrayType的实际参数类型，T是TypeVariable
    private List<String>[] listArray;//GenericArrayType，List<String>是ParameterizedType
    private List<? extends Number>[] wildArray;//GenericArrayType;List<? extends Number>是ParameterizedType;? extends Number是WildcardType
    private int[] intArray;//Class
    private String[] strArray;//Class

    static class Holder<V> {
    }
}
