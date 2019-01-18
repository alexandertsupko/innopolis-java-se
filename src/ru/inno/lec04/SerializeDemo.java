package ru.inno.lec04;

import ru.inno.lec04.entity.Line;
import ru.inno.lec04.entity.Person;
import ru.inno.lec04.entity.Point;

import java.io.*;

public class SerializeDemo {

    public static final String PERSON_OBJ = "./person.obj";

    public static void main(String[] args) {
        Person person = new Person("Tom", 35, 1.75, true, "12345");
        storePersonData(person);
        storePersonObject(person);

        Person.staticString = "123123123";
        Person readed = getPersonObject();
        System.out.println(person);
        System.out.println(readed);

        linesDemo();
    }

    private static void linesDemo() {
        Point point1 = new Point(1.0, 1.0);
        Point point2 = new Point(2.0, 2.0);
        Point point3 = new Point(3.0, 3.0);
        Line line1 = new Line(point1, point2, 1);
        Line line2 = new Line(point2, point3, 2);
        String fileName = "./lines.bin";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(line1);
            oos.writeObject(line2);
            line1.setIndex(3);
            oos.writeObject(line1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < 3; i++) {
                Object line = ois.readObject();
                System.out.println(line);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Person getPersonObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PERSON_OBJ))) {
            Object o = ois.readObject();
            return (Person) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void storePersonObject(Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSON_OBJ))) {
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void storePersonData(Person person) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("./person.bin"))) {
            dos.writeUTF(person.getName());
            dos.writeInt(person.getAge());
            dos.writeDouble(person.getHeight());
            dos.writeBoolean(person.isMarried());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
