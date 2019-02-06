/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework06;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Домашняя работа №6 к теме "Механизмы отражения и проксирования".
 *
 * @author Александр Цупко
 */
public class Serialization implements SerializableToFile {
    private Class<?> clazz = null;

    /**
     * Создаёт файл, в котором записан XML-код структуры объекта, переданного в первом аргументе,
     * в расположении файловой системы, переданном во втором аргументе.
     * Если файл уже был в этом расположении, он перезаписывается.
     *
     * @param object объект, который нужно сериализовать
     * @param file   путь, заканчивающийся именем файла с расширением <tt>xml</tt>
     */
    @Override
    public void serialize(Object object, String file) {
        try (Writer writer = new PrintWriter(file)) {
            this.clazz = object.getClass();
            writer.append("<object").append(" type=\"").append(this.clazz.getSimpleName()).append("\">").append(System.lineSeparator());
            Field[] fields = this.clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                writer.append("\t<field")
                        .append(" type=\"").append(field.getType().getSimpleName()).append("\"")
                        .append(" name=\"").append(field.getName()).append("\"")
                        .append(" value=\"").append(String.valueOf(field.get(object))).append("\" />")
                        .append(System.lineSeparator());
            }
            writer.append("</object>");
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает объект, структура которого получается в результате восстановления из XML-кода,
     * содержащегося в расположении файловой системы, переданном в аргументе.
     *
     * @param file путь, заканчивающийся именем файла с расширением <tt>xml</tt>
     * @return восстановленный объект
     */
    @Override
    public Object deSerialize(String file) {
        String contents = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder currentContents = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                currentContents.append(line);
            }
            contents = currentContents.toString(); // текстовое содержимое файла
        } catch (IOException e) {
            e.printStackTrace();
        }

        contents = contents.substring(contents.indexOf("object")); // find <object> tag
        int beginIndex = contents.indexOf("\""); // find the opening quote
        int endIndex = contents.indexOf("\"", ++beginIndex); // find the closing quote
        String objectType = contents.substring(beginIndex, endIndex); // extract the type of the object
        contents = contents.substring(++endIndex); // delete the parsed piece of text

        int fieldsNumber = contents.split("field").length - 1; // number of fields: "... [field ...] [field ...]"
        int attributesNumber = (contents.split("=").length - 1) / fieldsNumber; // number of attributes
        String[][] fields = new String[fieldsNumber][attributesNumber]; // 0 'type', 1 'name', 2 'value'

        if (this.clazz.getSimpleName().equals(objectType)) { // if this is the type that was previously serialized then
            for (int i = 0; i < fieldsNumber; i++) { // for every field
                contents = contents.substring(contents.indexOf("field")); // find <field> tag
                for (int j = 0; j < attributesNumber; j++) { // for every attribute
                    beginIndex = contents.indexOf("\""); // find the opening quote
                    endIndex = contents.indexOf("\"", ++beginIndex); // find the closing quote
                    fields[i][j] = contents.substring(beginIndex, endIndex); // extract the piece between the quotes
                    contents = contents.substring(++endIndex); // delete the parsed piece of text
                }
            }
        }

        Object object = null;
        try {
            object = this.clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] declaredFields = this.clazz.getDeclaredFields();
        for (int i = 0; i < fieldsNumber; i++) {
            if (declaredFields[i].getType().getSimpleName().equals(fields[i][0])) {
                declaredFields[i].setAccessible(true);
                try {
                    declaredFields[i].set(object, fields[i][0].equals("int") ? Integer.parseInt(fields[i][2]) : fields[i][2]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}
