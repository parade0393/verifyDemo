package demo.feflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParameterizedTypeTest {
    private Map<String, ParameterizedTypeTest> map;
    private Set<String> set1;
    private Class<?> clz;
    private Holder<String> holder;
    private List<String> list;
    private ArrayList<String> arrayList;
    private Map.Entry<String, String> entry;

    private String str;
    private Integer i;
    private Set set;
    private List aList;

    static class Holder<V> {
    }
}
