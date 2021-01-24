package demo;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class Node{
    private String id;
    private String name;
    private int sortNum;

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node(String id, String name, int sortNum) {
        this.id = id;
        this.name = name;
        this.sortNum = sortNum;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getId().equals(node.getId()) &&
                getName().equals(node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sortNum=" + sortNum +
                '}';
    }

    /*@Override
    public int compareTo(@NotNull Node o) {
        return 0;
    }*/
}
