package demo;

public class TestJava {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String result = "";
//        for (int i = 0; i < 100000; i++) {
//            result+="6";
//        }//4427毫秒
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            stringBuilder.append("6");
        }
        System.out.println(stringBuilder);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}

