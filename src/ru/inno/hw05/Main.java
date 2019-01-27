package ru.inno.hw05;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Вспомогательный класс для тестирования и отладки.
 *
 * @author Александр Цупко
 */
public class Main {
    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // получаем количество источников из командной строки или берём по умолчанию 1 000 источников
        final int SOURCES_QUANTITY = args.length > 0 ? Integer.parseInt(args[0]) : 1_000;
        // создаём пустой массив размером по заданному количеству источников
        String[] sources = new String[SOURCES_QUANTITY];
        // каждый источник выбирается случайно из проекта Гутенберга и кладётся последовательно в массив
        for (int i = 0; i < sources.length; i++) {
            int id = ThreadLocalRandom.current().nextInt(50_000); // доступно 50 000 текстов
            sources[i] = "http://gutenberg.org/zipcat2.php/" + id + "/" + id + "-0.txt";
        }
        // создаём экземпляр тестируемого класса
        FindGivenWordsInTheSources finder = new FindGivenWordsInTheSources();
        // вызываем тестируемый метод
        finder.getOccurrences(sources, new String[]{"excruciating"}, "./src/ru/inno/hw05/result.txt");
    }
}
