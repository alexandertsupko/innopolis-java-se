package ru.inno.lec03;

import ru.inno.lec03.entity.Skill;
import ru.inno.lec03.entity.Student;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List students = getStudent();
        for (int i = 0; i < students.size(); i++) {
            Student stu = (Student) students.get(i);
            if (i == 2) {
                students.remove(i);
            }
            System.out.println(stu.getFirstName());
        }

        // for-each loop
        for (Object student : students) {
            Student stu = (Student) student;
            System.out.println(stu.getFirstName());
        }

        // map
        Map stuskills = generateSkills(students);
        Student x = (Student) students.get(0);
        // x.setLastName("McGregor");
        System.out.println(x.getLastName() + " : " + stuskills.get(x));
    }

    private static Map generateSkills(List students) {
        Map result = new HashMap();
        for (Object student : students) {
            result.put(student, new Skill("Java", (int) (Math.random() * 10)));
        }
        return Collections.unmodifiableMap(result);
    }

    private static List getStudent() {
        return Arrays.asList(new Student[]{
                new Student("John", "Connor"),
                new Student("Ivan", "Ivanov"),
                new Student("Vlad", "Vladov"),
                new Student("John", "Smith")
        });
    }
}
