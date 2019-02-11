/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment09;

import java.io.IOException;

/**
 * Домашняя работа №9 к теме "Нововведения в Java 7 и 8".
 *
 * @author Александр Цупко
 */
public class Main {
    private static final String PATH = "./src/main/resources/"; // путь к ресурсам
    private static String[] sources = new String[] {
            PATH + "input.txt", // файл в ресурсах
            "http://gutenberg.org/zipcat2.php/1342/1342-0.txt" // Джейн Остин, "Гордость и предубеждение"
    };
    private static String[] words = new String[] {
            "second", // искомые слова
    };

    /**
     * Тестирующий и отладочный клиент.
     *
     * @param args аргументы командной строки не используются
     */
    public static void main(String[] args) throws IOException {
        FindGivenWordsInTheSourcesStreamAPI example = new FindGivenWordsInTheSourcesStreamAPI();
        example.getOccurrences(sources, words, PATH + "output.txt");
    }
}
