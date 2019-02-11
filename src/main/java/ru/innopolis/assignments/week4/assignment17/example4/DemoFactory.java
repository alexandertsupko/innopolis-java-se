/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example4;

import ru.innopolis.assignments.week2.assignment09.FindGivenWordsInTheSourcesStreamAPI;
import ru.innopolis.assignments.week4.assignment17.example4.factory.FileReader;
import ru.innopolis.assignments.week4.assignment17.example4.factory.SourceReader;
import ru.innopolis.assignments.week4.assignment17.example4.factory.URLReader;

/**
 * Клиентский код.
 */
public class DemoFactory extends FindGivenWordsInTheSourcesStreamAPI {
    private SourceReader sourceReader = null;

    private String[] sources = new String[] {"input.txt", "http://gutenberg.org/zipcat2.php/1342/1342-0.txt"};
    private String[] words = new String[] {"that", "Apple", "first", "second", "a"};

    public DemoFactory() {
        for (String source : sources) {
            if (source.startsWith("http://")) {
                sourceReader = new URLReader();
            } else {
                sourceReader = new FileReader();
            }
            sourceReader.getContents(source);
        }
    }

    public static void main(String[] args) {
        new DemoFactory();
    }
}
