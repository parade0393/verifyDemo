package demo.thread.cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * FieldUpdater的字段必须是volatile
 */
public class FieldUpdater {
    public static void main(String[] args) {
        Student student = new Student();
        AtomicReferenceFieldUpdater<Student, String> name = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        boolean parade = name.compareAndSet(student, null, "parade");

    }
}

class Student {
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
