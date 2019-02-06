/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example4;

import ru.innopolis.homeworks.week2.homework09.FindGivenWordsInTheSourcesStreamAPI;

/**
 * Клиентский код.
 */
public class DemoFactory extends FindGivenWordsInTheSourcesStreamAPI {
    private String[] sources = new String[] {"text.txt", "http://gutenberg.org/zipcat2.php/1342/1342-0.txt"};
    private String[] words = new String[] {"that", "Apple", "first", "second", "a"};

    public DemoFactory() {
        getOccurrences(sources, words, null);
    }

    public static void main(String[] args) {
        new DemoFactory();
    }
}
