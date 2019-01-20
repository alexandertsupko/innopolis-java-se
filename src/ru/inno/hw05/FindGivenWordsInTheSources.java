package ru.inno.hw05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(res)))) {
            for (String source : sources) {
                String[] sentences = source.split("[.?!]");
                for (String sentence : sentences) {
                    sentence = sentence.trim();
                    if (sentence.isEmpty()) {
                        continue;
                    }
                    String sentenceLowerCase = sentence.toLowerCase();
                    for (String word : words) {
                        if (sentenceLowerCase.contains(word)) {
                            printWriter.println(sentence);
                        }
                    }
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
                        "This is my first source! And this is the second sentence.",
                        "This is the second source. And here is its second sentence, too!"
                },
                new String[] {
                        "This", "apple"
                }, "./result.txt"
        );
    }
}
