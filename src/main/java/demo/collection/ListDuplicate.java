package demo.collection;

import demo.Node;

import java.util.ArrayList;
import java.util.List;

//list去重
public class ListDuplicate {
    public static void main(String[] args) {
        List<Node> demo1 = new ArrayList<>();
        demo1.add(new Node("1", "demo1"));
        demo1.add(new Node("2", "demo2"));
        demo1.add(new Node("3", "demo3"));

        List<Node> demo2 = new ArrayList<>();
        demo2.add(new Node("1", "demo1"));
        demo2.add(new Node("2", "demo2"));
        demo2.add(new Node("5", "demo3"));

        //第一：Set去重，需要重写equals和hashcode
       /* Set<Node> set = new HashSet<>();
        set.addAll(demo1);
        set.addAll(demo2);
        System.out.println(set);*/

        //第二，map去重，不需要重写equals和hashcode
       /* Map<String, Node> map = new HashMap<>();

        for (Node node : demo1) {
            map.put(node.getId(), node);
        }

        for (Node node : demo2) {
            String id = node.getId();
            if (!map.containsKey(id)){
                map.put(id, node);
            }
        }

        List<Node> result = new ArrayList<>(map.values());
        System.out.println(result);*/

        //第三，同样需要重写equals和hashcode
        demo1.removeAll(demo2);
        demo1.addAll(demo2);
        System.out.println(demo1);
    }
}
