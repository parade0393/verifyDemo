package demo.feflect;

import java.util.List;
import java.util.Map;

public class WildcardTypeTest {
    private List<? extends Number> listUpper;
    private List<? super String> listLower;
    private List<String> list;

    private Map<? extends String, ? super Number> map1;
    private Map<? extends String, ?> map2;

    private Class<?> clazz;
    // 不写泛型的list
    private List objList;
}
