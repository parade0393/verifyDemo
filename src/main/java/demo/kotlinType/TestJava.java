package demo.kotlinType;

import java.util.Map;

public class TestJava {
    int i = 0;
    void reportError(){
        throw new RuntimeException();
    }
    private void example(){
        reportError();//抛出错误
        i = 2;//这行是无法执行的，但是编译器不会提出警告
    }

    private void exampleTwo(Map<String,String> map){
//        String data = map.containsKey("key")? map.get("key") : reportError();//编译失败，需要返回String,实际是void
    }
}
