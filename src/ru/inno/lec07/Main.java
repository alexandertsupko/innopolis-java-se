package ru.inno.lec07;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        StringBuilder sb = new StringBuilder();
        String className = "GoodMagic2";
        sb.append("package ru.inno.lec07;public class ").append(className).append(" {{System.out.println(\"Хоттабыч2\");}}");
        try {
            String filename = "./" + className + ".java";
            // Сохраняем исходный код в файл
            Files.write(Paths.get(filename), sb.toString().getBytes());
            // Получаем компилятор
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            // Указываем имя .java файла
            String[] javacOpts = {filename};
            javac.run(null, null, null, javacOpts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Сейчас будет магия");
        Magic magic = new RudeMagic();
        magic.doMagic();

        ClassLoader cl = new MyClassLoader();
        Class<?> kindClass = cl.loadClass("ru.inno.lec07.KindMagic");
        Magic kindMagic = (Magic) kindClass.newInstance();
        kindMagic.doMagic();

//        KindMagic km1 = new KindMagic();
//        KindMagic km2 = (KindMagic) kindMagic;

        System.out.println("усё");
    }
}
