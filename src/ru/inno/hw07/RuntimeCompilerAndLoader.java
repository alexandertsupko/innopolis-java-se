package ru.inno.hw07;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static ru.inno.hw07.PathsAndNamesConstants.*;

public class RuntimeCompilerAndLoader {
    private static final String STOP_COMMAND = "exit";

    public static void main(String[] args) {
        StringBuilder sourceCode = new StringBuilder("package " + PACKAGE.getString() + ";");
        sourceCode
                .append("public class ").append(SIMPLE_NAME.getString()).append(" implements Worker{")
                .append("@Override public void doWork(){");
        // читаем реализацию метода doWork() построчно из консоли с помощью класса Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите реализацию метода doWork(), а затем " + STOP_COMMAND + ":");
            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine();
                if (line.length() == STOP_COMMAND.length() && line.equalsIgnoreCase(STOP_COMMAND)) {
                    break;
                }
                sourceCode.append(line); // добавляем считанную строку исходного кода
            }
        }
        sourceCode.append("}}"); // завершаем исходный код, а также обеспечиваем пустую реализацию по умолчанию
        try {
            // сохраняем исходный код в виде массива байт в файл FILE_NAME по адресу PATH
            Files.write(Paths.get(FULLY_QUALIFIED_FILE_NAME.getString()), sourceCode.toString().getBytes());
            // получаем системный компилятор
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            // запускаем компилятор для файла FILE_NAME, сохранённого по адресу PATH
            compiler.run(null, null, null, FULLY_QUALIFIED_FILE_NAME.getString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // получаем собственный загрузчик классов
        ClassLoader customClassLoader = new CustomClassLoader();
        try {
            // получаем объект метакласса подгружаемого из пути FULLY_QUALIFIED_NAME класса
            Class<?> clazz = customClassLoader.loadClass(FULLY_QUALIFIED_NAME.getString());
            // создаём экземпляр подгруженного класса, используя его метакласс
            Worker object = (Worker) clazz.newInstance();
            // вызываем метод, реализация которого была введена с консоли
            object.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
