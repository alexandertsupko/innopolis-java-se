package ru.inno.hw09;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
        Set<String> setOfWords = Arrays.stream(words)
                .filter(s -> !Objects.isNull(s))
                .filter(s -> !s.trim().isEmpty())
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(HashSet::new));
        Set<String> setOfSentences = Arrays.stream(sources)
                .parallel()
                .flatMap(s -> Arrays.stream(s.split("[.!?]")))
                .filter(s -> !Objects.isNull(s))
                .filter(s -> !s.trim().isEmpty())
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(HashSet::new));
        for (String sentence : setOfSentences) {
            if (Arrays.stream(sentence.split("[\\s+,;:'\"-]"))
                    .distinct()
                    .anyMatch(word -> setOfWords.contains(word) /* strictly identical words */
                    || setOfWords.stream().anyMatch(word::contains) /* given word may be a substring of the found */)
            ) {
                System.out.println(sentence);
            }
        }
    }
}
