package demo.collection;

import demo.User;

import java.util.LinkedHashSet;

public class Unique {
    public static void main(String[] args) {
        User user1 = new User(1, "parade");
        User user2 = new User(2, "0393");
        User user3 = new User(3, "03931");
        User user4 = new User(4, "03931234");
        User user5 = new User(5, "5");
        LinkedHashSet<User> users = new LinkedHashSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        System.out.println(users);
        users.remove(user5);
        users.remove(user2);
        System.out.println(users);
    }
}
