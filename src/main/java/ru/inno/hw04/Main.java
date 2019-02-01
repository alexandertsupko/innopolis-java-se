package ru.inno.hw04;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Вспомогательный класс для тестирования и отладки.
 *
 * @author Александр Цупко
 */
public class Main {
    private static final String PATH = "./src/ru/inno/hw04/"; // путь, в котором нужно создать файлы

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // количество слов в массиве words передаётся из командной строки, либо выбирается от 1 до 1 000
        final int WORDS_QUANTITY = args.length > 0 ? Integer.parseInt(args[0]) : ThreadLocalRandom.current().nextInt(1_000) + 1;
        // создание массива words размером по заданному количеству и инициализация null
        String[] words = new String[WORDS_QUANTITY];
        // создание экземпляра тестируемого класса
        TextFileGenerator generator = new TextFileGenerator();
        // инициализация массива
        for (int i = 0; i < words.length; i++) {
            words[i] = generator.generateWord(); // заполнение массива words случайными словами
        }
        // вызов тестируемого метода
        generator.getFiles(PATH, 3, 10, words, 5);
    }
}
