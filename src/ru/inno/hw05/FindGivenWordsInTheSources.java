package ru.inno.hw05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FindGivenWordsInTheSources {

    /**
     * Находит вхождения всех слов из данного массива в данные текстовые источники,
     * при этом записывает в файл по данному адресу предложения, в которых встретилось какое-либо слово.
     *
     * <p> Предложение - это последовательность слов, состоящих из кириллических или латинских символов,
     * начинающаяся с заглавной буквы и заканчивающаяся точкой, вопросительным или восклицательным знаками,
     * или многоточием. Внутри предложения могут находиться знаки препинания.
     *
     * <p> Ограничения: размеры обоих массивов не могут превышать 2000 элементов.
     * Ресурсы могут быть размером от 1 кБ до 1 ГБ.
     *
     * @param sources массив адресов источников (файлы, FTP, web-ссылки)
     * @param words   массив искомых слов
     * @param res     адрес файла для записи результата
     */
    public static void getOccurrences(String[] sources, String[] words, String res) throws IOException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2_000);
        for (String word : words) {
            queue.add(word.toLowerCase());
        }
        ExecutorService service = null;
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(res)))) {
            service = Executors.newFixedThreadPool(sources.length);
            List<Future<?>> futures = new ArrayList<>(sources.length);
            for (String source : sources) {
                Future<?> future = service.submit(() -> findGivenWordsInTheSource(queue, printWriter, source));
                futures.add(future);
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private static void findGivenWordsInTheSource(BlockingQueue<String> queue, PrintWriter printWriter, String source) {
        String[] sentences = source.split("[.?!]");
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.isEmpty()) {
                continue;
            }
            String sentenceLowerCase = sentence.toLowerCase();
            for (String word : queue) {
                if (sentenceLowerCase.contains(word)) {
                    printWriter.println(sentence);
                }
            }
        }
    }

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws IOException {
        getOccurrences(
                new String[] {
                        "This is - my first source? And this is: the второе sentence. ",
                        " This is \"the second источник\"... And here is его 'second sentence', too!"
                },
                new String[] {
                        "This", "apple", "too"
                }, "./result.txt"
        );
    }
}
