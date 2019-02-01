package ru.inno.lec06;

import ru.inno.lec06.entity.Human;
import ru.inno.lec06.entity.HumanAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) throws IllegalAccessException {
        Class<Human> clazz = Human.class;
        Human human = new Human();
        System.out.println(human);

        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());

        System.out.println(Arrays.toString(clazz.getFields()));

        Field[] fields = clazz.getDeclaredFields();
        for (Field declaredField : fields) {
            System.out.println(declaredField.getName() + " " + Modifier.isFinal(declaredField.getModifiers()));
            declaredField.setAccessible(true);
        }

        // чтение private-поля
        String name = (String) fields[1].get(human);
        System.out.println(name);

        // изменение
        fields[1].set(human, "Коля");
        fields[0].set(human, "Пришелец");
        fields[2].setInt(human, 500); // final fields cannot be changed!

        System.out.println(human);

        // работа с аннотациями
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("Аннотация " + annotation);
        }
        HumanAnnotation annotation = clazz.getAnnotation(HumanAnnotation.class);
        if (annotation != null) {
            System.out.println(annotation.name());
        }
    }
}
