package demo.collection;

import demo.User;

import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }
        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println("key = "+next.getKey()+"and value= "+next.getValue());
        }


        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println("key = "+stringStringEntry.getKey()+"and value= "+stringStringEntry.getValue());
        }
        System.out.println("------------------------------");
        Collection<String> values = map.values();
        List<String> collList = new ArrayList<>(values);
        System.out.println(collList);
        System.out.println("-----------深拷贝-----------");
        HashMap<String, String> map1 = new HashMap<>();
        map1.putAll(map);
        map.remove("1");
        System.out.println(map);
        System.out.println(map1);
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put("1", new User(1, "name1"));
        hashMap.put("2", new User(2, "NAME2"));
        HashMap<String, User> copyHashMap = new HashMap<>(hashMap);
        hashMap.remove("1");
        System.out.println(hashMap);
        System.out.println(copyHashMap);

        System.out.println("****************");
        String s = map.get("4");
        System.out.println(s);
    }
}
