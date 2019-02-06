/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.innopolis.homeworks.week2.homework07.PathsAndNamesConstants.*;

/**
 * Вспомогательный загрузчик классов для загрузки байт-кода в JVM по простому имени переданного класса.
 *
 * @author Александр Цупко
 */
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (SIMPLE_NAME.getString().equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(FULLY_QUALIFIED_BYTE_NAME.getString()));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
