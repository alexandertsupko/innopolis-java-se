/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09;

/**
 * Домашняя работа №9 к теме "Нововведения в Java 7 и 8".
 *
 * @author Александр Цупко
 */
public class Main {
    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки не используются
     */
    public static void main(String[] args) {
        FindGivenWordsInTheSourcesStreamAPI example = new FindGivenWordsInTheSourcesStreamAPI();
        example.getOccurrences(
                new String[] {
                        "This is - my firstsource? And this is : the второе sentence. ",
                        " This is    \"the second источник\"... And here is его 'second sentence', too!",
                        null, "", "-"
                },
                new String[] {
                        "That", "second", "a", "first", "-", "", null
                }, null
        );
    }
}
