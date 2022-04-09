package demo.sort;

public class StudentAsc implements Comparable<StudentAsc>{
    private String name;

    private Integer age;

    public StudentAsc(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int compareTo(StudentAsc o) {
        if(null == this.age) {
            return -1;
        }
        if(null == o.getAge()) {
            return 1;
        }
        return this.age.compareTo(o.getAge());
    }

    @Override
    public String toString() {
        return "StudentAsc{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
