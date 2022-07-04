package demo.collection;

import demo.Node;
import demo.NodeParent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortDemo {
    public static void main(String[] args) {
        List<Node> demo2 = new ArrayList<>();
        demo2.add(new Node("1", "demo1",2));
        demo2.add(new Node("5", "demo5",2));
        demo2.add(new Node("2", "demo2",1));
        demo2.add(new Node("3", "demo3",3));

        NodeParent nodeParent = new NodeParent();
        nodeParent.setChildren(demo2);
        System.out.println(nodeParent.getChildren());
//        System.out.println(demo2);//[Node{id='1', name='demo1'}, Node{id='5', name='demo5'}, Node{id='2', name='demo2'}, Node{id='3', name='demo3'}]
//        demo2.sort(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());//按照某个字段升序
//            }
//        });
//
//        System.out.println(demo2);
//
//        demo2.sort(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                int flag = o1.getSortNum() - o2.getSortNum();//先按sortNum字段值升序排序，再按id升序排序
//                if (flag == 0){
//                    //如果sortNum值相同，再按id升序
//                    //需要降序则只需要用o2-o1即可
//                    flag = Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
//                }
//                return flag;
//            }
//        });
//
//        System.out.println(demo2);
        //当然Node实体类也可以实现Comparator接口，这样就可以直接demo.sort()了;

        Collections.sort(nodeParent.getChildren(), new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());//按照某个字段升序
            }
        });

        System.out.println(nodeParent.getChildren());

    }
}
