/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, содержащий логику поиска заданного массива слов в заданном массиве источников.
 *
 * @author Александр Цупко
 */
public class FindGivenWordsInTheSourcesStreamAPI {
    /**
     * Находит вхождения всех слов из данного массива в данные текстовые источники
     * и записывает предложения, в которых встретилось какое-либо слово.
     *
     * @param sources массив адресов источников
     * @param words   массив искомых слов
     * @param res     адрес файла для записи результата
     */
    public void getOccurrences(String[] sources, String[] words, String res) {
        Set<String> setOfWords = Collections.synchronizedSet(Arrays.stream(words)
                .parallel() // check if it influences the performance
                .filter(Objects::nonNull)
                .filter(word -> !word.trim().isEmpty())
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(HashSet::new)));
        Set<String> setOfSentences = Arrays.stream(sources)
                .parallel() // check if it influences the performance
                .filter(Objects::nonNull)
                .flatMap(source -> Arrays.stream(source.split("[.!?]")))
                .map(String::trim)
                .collect(Collectors.toCollection(HashSet::new));
        // todo: add ExecutorService to take every single sentence into a separate thread
        for (String sentence : setOfSentences) {
            synchronized (setOfWords) { // required to do so by documentation for synchronizedSet() method
                if (Arrays.stream(sentence.split("\\W")) // non-word characters, i.e. punctuation marks
                        .map(String::toLowerCase)
                        .distinct()
                        .anyMatch(setOfWords::contains)) {
                    System.out.println(sentence);
                }
            } // check if it influences the performance
        }
    }
}
