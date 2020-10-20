package demo.annotation;

public class JsonFiledTest {
    public static void main(String[] args) throws IllegalAccessException {
        Writer writer = new Writer(45, "parade岁月", "Android开发");
        System.out.println(JsonSerializer.serialize(writer));
    }
}
