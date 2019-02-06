/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week1.homework04;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Вспомогательный класс для тестирования и отладки.
 *
 * @author Александр Цупко
 */
public class Main {
    // путь, в котором нужно создать файлы
    private static final String PATH = "./target/classes/ru/innopolis/homeworks/week1/homework04/";

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
