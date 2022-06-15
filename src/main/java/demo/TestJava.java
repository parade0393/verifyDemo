package demo;

import java.util.LinkedList;
import java.util.List;

public class TestJava {
    public static void main(String[] args) {
        List<User> users = new LinkedList<>();
        User user = new User(2, "parade2");
        users.add(new User(1, "parade1"));
        users.add(user);
        boolean remove = users.remove(new User(4, "parade2"));
        System.out.println(remove);
        System.out.println(users);
    }
}

