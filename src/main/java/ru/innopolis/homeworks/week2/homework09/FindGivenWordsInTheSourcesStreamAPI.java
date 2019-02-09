/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09;

import ru.innopolis.homeworks.week2.homework09.factory.FileReaderFactory;
import ru.innopolis.homeworks.week2.homework09.factory.SourceReaderFactory;
import ru.innopolis.homeworks.week2.homework09.factory.URLReaderFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Класс, содержащий логику поиска заданного массива слов в заданном массиве источников.
 *
 * @author Александр Цупко
 */
public class FindGivenWordsInTheSourcesStreamAPI {
    private Set<String> setOfSentences = new HashSet<>(); // множество всех предложений из всех источников

    /**
     * Находит вхождения всех слов из данного массива в данные текстовые источники
     * и записывает предложения, в которых встретилось какое-либо слово.
     *
     * @param sources массив адресов источников
     * @param words массив искомых слов
     * @param res адрес файла для записи результата
     */
    public void getOccurrences(String[] sources, String[] words, String res) throws IOException {
        final Set<String> setOfWords = Collections.synchronizedSet(getSetOfWords(words));
        getSetOfSentences(sources);
        searchForWordsInTheSentences(setOfWords, setOfSentences, res);
    }

    private void searchForWordsInTheSentences(Set<String> setOfWords, Set<String> setOfSentences, String res) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(res))) {
            for (String sentence : setOfSentences) {
                if (Arrays.stream(sentence.split("\\W")) // всё, кроме букв, цифр и знаков подчёркивания
                        .map(String::toLowerCase)
                        .distinct()
                        .anyMatch(setOfWords::contains)) {
                    writer.println(sentence);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSetOfSentences(String[] sources) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(sources.length);
            List<Callable<Collection<String>>> tasks = new ArrayList<>(sources.length);
            Arrays.stream(sources)
                    .filter(Objects::nonNull)
                    .forEach(source -> tasks.add(() -> getSentences(source)));
            service.invokeAll(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private Collection<String> getSentences(String source) throws IOException {
        SourceReaderFactory readerFactory = getSourceReaderFactory(source);
        return Arrays.stream(readerFactory.getContents(source).split("[.!?]"))
                .map(String::trim)
                .collect(Collectors.toCollection((Supplier<Collection<String>>) () -> setOfSentences));
    }

    private SourceReaderFactory getSourceReaderFactory(String source) throws IOException {
        SourceReaderFactory readerFactory;
        if (source.startsWith("http://")) {
            readerFactory = new URLReaderFactory();
        } else {
            readerFactory = new FileReaderFactory();
        }
        return readerFactory;
    }

    private Set<String> getSetOfWords(String[] words) {
        return Arrays.stream(words)
                    .parallel()
                    .filter(Objects::nonNull)
                    .filter(word -> !word.trim().isEmpty())
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toCollection(HashSet::new));
    }
}
