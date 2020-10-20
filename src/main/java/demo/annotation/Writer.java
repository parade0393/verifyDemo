package demo.annotation;

public class Writer {
    private int age;
    @JsonFiled("writerName")
    private String name;
    @JsonFiled
    private String bookName;

    public Writer(int age, String name, String bookName) {
        this.age = age;
        this.name = name;
        this.bookName = bookName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
