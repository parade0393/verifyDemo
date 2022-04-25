package demo.stream;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        String city = streamTest.getCity(null);
        String city8 = streamTest.getCity8(null);
        System.out.println(city);
        System.out.println(city8);

        Person person = null;
        if (person != null){

        }
        //Android使用此Api需要7.0以上
        Optional.ofNullable(person).ifPresent( u ->{
            //不为空
        });

        Optional.ofNullable(person)
                .filter( p -> "zhangsan".equals(p.getName()))
                .orElseGet(Person::new);
    }
}
