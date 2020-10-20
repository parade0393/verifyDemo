package demo.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSerializer {
   public static String serialize(Object object) throws IllegalAccessException {
       Map<String, String> jsonElements = new HashMap<>();
       Class<?> objectClass = object.getClass();

       for (Field declaredField : objectClass.getDeclaredFields()) {//遍历所有属性，不包括父类
           declaredField.setAccessible(true);
           if (declaredField.isAnnotationPresent(JsonFiled.class)){//属性是否有注解修饰
               System.out.println(declaredField.getName());
               jsonElements.put(getSerializedKey(declaredField), (String) declaredField.get(object));
           }
       }

       return toJsonString(jsonElements);
   }

    /**
     * 获取属性上注解的值 如果注解是空的值是空的则返回字段名称
     * @param field 属性
     * @return 注解的值
     */
   private static String getSerializedKey(Field field){
//       field.getAnnotation(JsonFiled.class) //获取注解对象
       String value = field.getAnnotation(JsonFiled.class).value();//获得字段上的注解的值
       if (value.isEmpty()){
           return field.getName();
       }else {
           return value;
       }
   }

    private static String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + elementsString + "}";
    }
}
