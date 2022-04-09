package demo;

public class TestJava {

    public static void main(String[] args) {
        String content = "010-9999999";//false
        content = "020-9999999";//true
        content = "020-9999";//false
        content = "010";//true
        content = "020";//false
        System.out.println(content.matches("010|020-\\d{7,8}"));
        //这样写的意思是010或者020-\d{7,8}
        //即选择符右边的是一组，并且matches是整体匹配
    }
}

