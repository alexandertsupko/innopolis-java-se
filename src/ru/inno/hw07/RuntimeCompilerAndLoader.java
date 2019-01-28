package ru.inno.hw07;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static ru.inno.hw07.PathsAndNamesConstants.*;

/**
 * Домашняя работа №7 к теме "Загрузчики классов".
 *
 * @author Александр Цупко
 */
public class RuntimeCompilerAndLoader {
    /**
     * Выполняет получение исходного кода из консоли, компиляцию во время исполнения и загрузку полученного байт-кода.
     */
    public RuntimeCompilerAndLoader() {
        String sourceCode = getSourceCode();
        compileSourceCode(sourceCode);
        loadClass();
    }

    /**
     * Выполняет загрузку байт-кода класса по имени {@code SIMPLE_NAME} (см. {@code PathsAndNamesConstants}).
     */
    private void loadClass() {
        try {
            // получаем собственный загрузчик классов
            ClassLoader customClassLoader = new CustomClassLoader();
            // получаем объект метакласса загружаемого по имени SIMPLE_NAME класса
            Class<?> clazz = customClassLoader.loadClass(SIMPLE_NAME.getString());
            // создаём экземпляр загруженного класса, используя его метакласс
            Worker object = (Worker) clazz.newInstance();
            // вызываем метод, реализация которого была введена с консоли
            object.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Компилирует исходный код, полученный в аргументе, в байт-код, лежащий по тому же пути, что и исходный код.
     * Компиляция происходит во время исполнения с помощью библиотечного компилятора Java.
     *
     * @param sourceCode исходный код, который нужно скомпилировать во время исполнения
     */
    private void compileSourceCode(String sourceCode) {
        try {
            // сохраняем исходный код в виде массива байт в файл FILE_NAME по адресу PATH
            Files.write(Paths.get(FULLY_QUALIFIED_FILE_NAME.getString()), sourceCode.getBytes());
            // получаем системный компилятор
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            // запускаем компилятор для файла FILE_NAME, сохранённого по адресу PATH
            compiler.run(null, null, null, FULLY_QUALIFIED_FILE_NAME.getString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает исходный код вводимого во время исполнения класса в виде объекта класса {@code String}.
     *
     * @return строковое представление исходного кода класса, введённого с консоли во время исполнения
     */
    private String getSourceCode() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode
                .append("public class ").append(SIMPLE_NAME.getString())
                .append(" implements ").append(PACKAGE.getString()).append("Worker {")
                .append("@Override public void doWork() {");
        // читаем реализацию метода doWork() построчно из консоли с помощью класса Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            String STOP_COMMAND = "exit";
            System.out.println("Введите реализацию метода doWork(), а затем " + STOP_COMMAND + ":");
            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine();
                if (STOP_COMMAND.equalsIgnoreCase(line)) {
                    break;
                }
                sourceCode.append(line); // добавляем считанную строку исходного кода
            }
        }
        sourceCode.append("}}"); // завершаем исходный код, а также обеспечиваем пустую реализацию по умолчанию
        return sourceCode.toString();
    }
}
