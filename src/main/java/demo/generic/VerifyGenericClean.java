package demo.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证泛型擦除
 */
public class VerifyGenericClean {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("name");//[name]
        System.out.println(list);
        try {
            Method add = list.getClass().getDeclaredMethod("add", Object.class);
            try {
                add.invoke(list, 1);
                add.invoke(list, true);
                System.out.println(list);//[name, 1, true]
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
