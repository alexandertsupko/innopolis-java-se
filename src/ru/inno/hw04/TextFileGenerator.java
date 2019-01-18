package ru.inno.hw04;

import java.util.Random;

public class TextFileGenerator {
    private static final char START_LETTER = 'a';
    private static final int ALPHABET_SIZE = 26;

    public static String generateWord() {
        StringBuilder word = new StringBuilder();
        for (int i = 0, word_length = (int) (Math.random() * 15) + 1; i < word_length; i++) {
            word.append((char) (START_LETTER + (int) (Math.random() * ALPHABET_SIZE)));
        }
        return word.toString();
    }

    public static String generateSentence() {
        Random random = new Random();
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
        switch (random.nextInt(3)) {
            case 0:
                sentence.append('.');
                break;
            case 1:
                sentence.append('?');
                break;
            case 2:
                sentence.append('!');
                break;
            default:
        }
        sentence.append(' ');
        return sentence.toString();
    }

    public static String generateParagraph() {
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0, paragraph_length = (int) (Math.random() * 20) + 1; i < paragraph_length; i++) {
            paragraph.append(generateSentence());
        }
        paragraph.append('\n').append('\r');
        return paragraph.toString();
    }

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println(generateWord());
            System.out.println(generateSentence());
            System.out.println(generateParagraph());
        }
    }
}
