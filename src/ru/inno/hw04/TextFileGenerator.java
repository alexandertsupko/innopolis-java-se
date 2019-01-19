package ru.inno.hw04;

import java.util.Random;

/**
 * Домашняя работа №4 к теме "Пакет java.io и работа с ресурсами".
 *
 * @author Александр Цупко
 */
public class TextFileGenerator {
    private static final char START_LETTER = 'a'; // начальная строчная латинская буква
    private static final int ALPHABET_SIZE = 26; // количество букв латинского алфавита
    private static Random random = new Random(); // генератор псевдослучайных чисел
    private static final char[] PUNCTUATION_MARKS = {'.', '!', '?'}; // возможные знаки в конце предложения

    /**
     *
     *
     * @return
     */
    public static String generateWord() {
        StringBuilder word = new StringBuilder();
        for (int i = 0, word_length = (int) (Math.random() * 15) + 1; i < word_length; i++) {
            word.append((char) (START_LETTER + (int) (Math.random() * ALPHABET_SIZE)));
        }
        return word.toString();
    }

    /**
     *
     *
     * @return
     */
    public static String generateSentence() {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0, sentence_length = (int) (Math.random() * 15) + 1; i < sentence_length; i++) {
            sentence.append(generateWord());
            if (i != sentence_length - 1) {
                if (random.nextBoolean()) {
                    sentence.append(',');
                }
                sentence.append(' ');
            }
        }
        sentence.setCharAt(0, Character.toUpperCase(sentence.charAt(0)));
        sentence.append(PUNCTUATION_MARKS[random.nextInt(3)]).append(' ');
        return sentence.toString();
    }

    /**
     *
     *
     * @return
     */
    public static String generateParagraph() {
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0, paragraph_length = (int) (Math.random() * 20) + 1; i < paragraph_length; i++) {
            paragraph.append(generateSentence());
        }
        paragraph.append('\n').append('\r');
        return paragraph.toString();
    }

    /**
     *
     *
     * @param path
     * @param n
     * @param size
     * @param words
     * @param probability
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) { // количество файлов
            for (int j = 0; j < size; j++) { // количество абзацев
                generateCustomParagraph(words, probability);
            }
        }
    }

    /**
     *
     *
     * @param words
     * @param probability
     */
    private static void generateCustomParagraph(String[] words, int probability) {
        for (int i = 0, paragraph_length = (int) (Math.random() * 20) + 1; i < paragraph_length; i++) {
            StringBuilder sentence = new StringBuilder();
            for (int j = 0, sentence_length = (int) (Math.random() * 15) + 1; j < sentence_length; j++) {
                if (Math.random() < 1.0 / probability) {
                    sentence.append(words[random.nextInt(words.length)]);
                } else {
                    sentence.append(generateWord());
                }
                if (j != sentence_length - 1) {
                    if (random.nextBoolean()) {
                        sentence.append(',');
                    }
                    sentence.append(' ');
                }
            }
            sentence.setCharAt(0, Character.toUpperCase(sentence.charAt(0)));
            sentence.append(PUNCTUATION_MARKS[random.nextInt(3)]).append(' ');
            System.out.print(sentence);
        }
        System.out.println('\r');
    }

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        getFiles("", 1, 2, new String[]{"a", "b", "c"}, 5);
//        for (int i = 0; i < 2; i++) {
//            System.out.println(generateWord());
//            System.out.println(generateSentence());
//            System.out.println(generateParagraph());
//        }
    }
}
