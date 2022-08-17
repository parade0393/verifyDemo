package demo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合删除的和增加的
 */
public class DiffMap {
    public static void main(String[] args) {
        List<Map<String, Object>> oldList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("areaKey", "LY");
        map1.put("HOST", "http");
        oldList.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("areaKey", "PY");
        map2.put("HOST", "http");
        oldList.add(map2);

        List<Map<String, Object>> newList = new ArrayList<>();

//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("areaKey", "LY");
//        map3.put("HOST", "http");
//        newList.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("areaKey", "AY");
        map4.put("HOST", "http");
        newList.add(map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("areaKey", "HB");
        map5.put("HOST", "https");
        newList.add(map5);

        //处理减少的
        List<Map<String, Object>> deleteList = new ArrayList<>();
        for (Map<String, Object> oldMap : oldList) {
            String oldAreaKey = (String) oldMap.get("areaKey");
            boolean exitsFlag = false;
            for (Map<String, Object> newMap : newList) {
                String newAreaKey = (String) newMap.get("areaKey");
                if (oldAreaKey.equals(newAreaKey)){
                    exitsFlag = true;
                    break;
                }
            }
            if (!exitsFlag){
                deleteList.add(oldMap);
            }
        }
        for (Map<String, Object> deleteMap : deleteList) {
            System.out.println(deleteMap.get("areaKey"));
        }

        //处理增加的
        List<Map<String, Object>> addList = new ArrayList<>();
        for (Map<String, Object> newMap : newList) {
            boolean exitsFlag = false;
            String newKey = (String) newMap.get("areaKey");
            for (Map<String, Object> oldMap : oldList) {
                String oldKey = (String) oldMap.get("areaKey");
                if (newKey.equals(oldKey)){
                    exitsFlag = true;
                    break;
                }
            }
            if (!exitsFlag){
                addList.add(newMap);
            }
        }
        System.out.println("---------------------");

        for (Map<String, Object> addMap : addList) {
            System.out.println("areaKey:"+addMap.get("areaKey")+"--HOST:"+addMap.get("HOST"));
        }
    }
}
