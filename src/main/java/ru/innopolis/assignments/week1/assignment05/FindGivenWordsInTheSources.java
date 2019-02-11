/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week1.assignment05;

import jdk.nashorn.api.scripting.URLReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Домашняя работа №5 к теме "Модель памяти Java. Потоки выполнения. Синхронизация".
 *
 * @author Александр Цупко
 */
public class FindGivenWordsInTheSources {
    /**
     * Преобразует переданный массив слов в блокирующую очередь из этих слов в нижнем регистре,
     * чтобы только один поток мог её проходить впоследствии.
     * Запускает {@code ExecutorService}, в который передаются задачи:
     * по одной задаче на каждый поток, количество потоков определяется количеством источников.
     * Затем метод ожидает окончания выполнения всех задач.
     *
     * @param sources массив адресов источников (файлы, FTP, web-ссылки)
     * @param words   массив искомых слов
     * @param res     адрес файла для записи результата
     */
    public void getOccurrences(String[] sources, String[] words, String res) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(words.length);
        for (String word : words) {
            queue.add(word.toLowerCase());
        }
        ExecutorService service = null;
        try (Writer writer = new PrintWriter(new BufferedWriter(new FileWriter(res)))) {
            service = Executors.newFixedThreadPool(sources.length);
            List<Callable<Void>> tasks = new ArrayList<>(sources.length);
            for (String source : sources) {
                tasks.add(() -> findGivenWordsInTheSource(queue, writer, source));
            }
            service.invokeAll(tasks);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    /**
     * Находит вхождения всех слов из данной блокирующей очереди в данный текстовый источник,
     * при этом записывает с помощью переданного извне потока вывода предложения, в которых
     * встретилось какое-либо слово.
     *
     * @param queue  блокирующая очередь для того, чтобы только один поток мог её проходить
     * @param writer переданный извне поток вывода, в который записываются результаты
     * @param source адрес текстового источника
     * @return обёртка типа {@code void}
     */
    private Void findGivenWordsInTheSource(BlockingQueue<String> queue, Writer writer, String source) {
        try (BufferedReader bufferedReader = new BufferedReader(new URLReader(new URL(source)))) {
            StringBuilder contents = new StringBuilder();
            bufferedReader.lines().forEach(contents::append);
            String[] sentences = contents.toString().split("[.?!]");
            for (String sentence : sentences) {
                sentence = sentence.trim();
                if (sentence.isEmpty()) {
                    continue;
                }
                String sentenceLowerCase = sentence.toLowerCase();
                for (String word : queue) {
                    if (sentenceLowerCase.contains(word)) {
                        writer.append(sentence).append(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
