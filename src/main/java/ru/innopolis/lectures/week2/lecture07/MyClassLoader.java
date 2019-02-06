package ru.innopolis.lectures.week2.lecture07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("ru.innopolis.lectures.week2.lecture07.KindMagic".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts working: " + name);
        if ("ru.innopolis.lectures.week2.lecture07.KindMagic".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("./KindMagic.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
