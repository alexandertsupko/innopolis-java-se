package ru.inno.hw04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Домашняя работа №4 к теме "Пакет java.io и работа с ресурсами".
 *
 * @author Александр Цупко
 */
public class TextFileGenerator {
    private static Random random = new Random(); // генератор псевдослучайных чисел

    private final char[] PUNCTUATION_MARKS = {'.', '!', '?'}; // возможные знаки в конце предложения

    /**
     * Генерирует слово, состоящее из случайно выбранных строчных латинских букв.
     * Длина слова выбирается в пределах от 1 до 15 букв.
     *
     * @return объект класса {@code String}, представляющий слово
     */
    public String generateWord() {
        StringBuilder word = new StringBuilder();
        for (int i = 0, word_length = random.nextInt(15) + 1; i < word_length; i++) {
            // начальная строчная латинская буква
            char START_LETTER = 'a';
            // количество букв латинского алфавита
            int ALPHABET_SIZE = 26;
            word.append((char) (START_LETTER + random.nextInt(ALPHABET_SIZE)));
        }
        return word.toString();
    }

    /**
     * Записывает в {@code n} файлов, находящихся по пути {@code path}, {@code size} абзацев,
     * сгенерированных в конечном счёте с помощью метода {@code generateWord()} по алгоритму,
     * заложенному в методах {@code generateSentence()} и {@code generateParagraph()}, но
     * так, что в среднем каждое {@code probability} слово (каждое второе, третье, четвёртое и т. д.)
     * в этих абзацах взято из переданного в аргументе {@code words} массива слов.
     *
     * @param path путь, в котором нужно создать {@code n} выходных файлов
     * @param n количество выходных файлов
     * @param size количество абзацев в этих файлах
     * @param words массив слов, из которого с заданной вероятностью выбирается случайное слово
     * @param probability целое порядковое число, показывающее как часто следует брать слово из {@code words}
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) { // количество файлов
            try (PrintWriter writer = new PrintWriter(path + i + ".txt")) {
                for (int j = 0; j < size; j++) { // количество абзацев
                    generateCustomParagraph(words, probability, writer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Генерирует абзац, в котором любое слово из {@code words} может встретиться в среднем через каждые
     * {@code probability} слов, то есть с вероятностью, равной {@code 1 / probability}.
     * Слова не из массива {@code words} генерируются методом {@code generateWord()}.
     *
     * @param words массив слов, из которого с заданной вероятностью выбирается случайное слово
     * @param probability целое порядковое число, показывающее как часто следует брать слово из {@code words}
     * @param writer объект, с помощью которого происходит запись в файлы
     */
    private void generateCustomParagraph(String[] words, int probability, PrintWriter writer) {
        for (int i = 0, paragraph_length = random.nextInt(20) + 1; i < paragraph_length; i++) {
            StringBuilder sentence = new StringBuilder();
            for (int j = 0, sentence_length = random.nextInt(15) + 1; j < sentence_length; j++) {
                if (random.nextDouble() < 1.0 / probability) {
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
            writer.print(sentence);
        }
        writer.println('\r');
    }
}
