package ru.inno.hw09;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FindGivenWordsInTheSourcesStreamAPI {
    public static void getOccurrences(String[] sources, String[] words, String res) {
        System.out.println(Arrays.toString(words));
        Set<String> setOfWords = Arrays.stream(words)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        System.out.println(setOfWords);
        Set<String> setOfSentences = Arrays.stream(sources)
                .parallel()
                .flatMap(s -> Arrays.stream(s.split("[.!?]")))
                .collect(Collectors.toSet());
        System.out.println(setOfSentences);
    }

    public static void main(String[] args) {
        getOccurrences(
                new String[] {
                        "This is - my first source? And this is: the второе sentence. ",
                        " This is \"the second источник\"... And here is его 'second sentence', too!"
                },
                new String[] {
                        "This", "apple", "too"
                }, null);
    }
}
