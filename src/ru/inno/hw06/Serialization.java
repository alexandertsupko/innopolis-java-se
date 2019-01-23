package ru.inno.hw06;

import ru.inno.lec06.entity.Human;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;

public class Serialization implements SerializableToFile {
    @Override
    public void serialize(Object object, String file) {
        try (Writer writer = new PrintWriter(file)) {
            Class<?> clazz = object.getClass();
            writer.append("<object").append(" type=\"").append(clazz.getSimpleName()).append("\">\n");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                writer.append("\t<field").append(" type=\"").append(field.getType().getSimpleName()).append("\" />\n");
            }
            writer.append("</object>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deSerialize(String file) {
        return null;
    }

    public static void main(String[] args) {
        Serialization example = new Serialization();
        example.serialize(new Human(), "./src/ru/inno/hw06/object.xml");
    }
}
