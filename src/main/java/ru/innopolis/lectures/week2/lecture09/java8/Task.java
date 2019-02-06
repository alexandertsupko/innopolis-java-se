package ru.innopolis.lectures.week2.lecture09.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Task {
    public static void main(String[] args) {
        System.out.println(sum(new String[]{"./src/ru/innopolis/lecture09/input.txt"}));
    }

    private static Integer sum(String[] sources) {
        return Arrays.stream(sources).parallel()
                .mapToInt(source -> {
                    try {
                        Stream<String> stream = Files.lines(Paths.get(source));
                        return stream.parallel()
                                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                                .mapToInt(Integer::parseInt)
                                .filter(value -> value > 0 && value % 2 == 0)
                                .sum();
                    } catch (IOException e) {
                        return 0;
                    }
                }).sum();
    }
}
