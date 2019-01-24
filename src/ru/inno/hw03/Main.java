package ru.inno.hw03;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*
        Создание и инициализация входящего массива
         */
        // количество элементов во входящем массиве из первого арумента командной строки, иначе значение по умолчанию
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        Integer[] a = new Integer[n]; // создание и инициализация пустого массива объектов класса Integer
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE); // заполнение случайными числами от 0 до 2^31-2
        }
        /*
        Проверка функциональных требований
         */
        MathBox<Integer> mathBox = new MathBox<>(a); // 1. Массив Integer[] передаётся в конструктор (дубликаты не добавляются)
        System.out.println("Сумма чисел в коллекции: " + mathBox.summator()); // 2. Метод summator() выводит сумму элементов подлежащей коллекции
        System.out.println(mathBox); // вывод на экран исходной коллекции
        double divisor = args.length > 1 ? Double.parseDouble(args[1]) : 3.14; // делитель из второго аргумента командной строки, иначе значение по умолчанию
        /*
        3. Метод splitter(Double) создаёт новую коллекцию путём деления каждого элемента старой коллекции на переданный в аргументе делитель.
        Класс StringBuilder используется для эффективного создания строкового представления новой коллекции
         */
        StringBuilder stringBuilder = new StringBuilder("Содержимое после деления на " + divisor + ": [ ");
        for (Object d : mathBox.splitter(divisor)) { // каждый элемент делится на переданный в аргументе делитель
            stringBuilder.append(String.format(Locale.US, "%.1f", d)).append(' ');
        }
        System.out.println(stringBuilder.append(']'));
        /*
        4. Проверка корректной работы методов equals() и hashCode() при хранении объектов этого класса в HashMap
         */
        Map<String, MathBox<Integer>> map = new HashMap<>(); // создать HashMap для хранения объектов этого класса в качестве значений
        map.put("first", mathBox); // добавить текущий объект MathBox в HashMap
        Integer[] b = new Integer[n]; // создать новый объект Integer[]
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * Integer.MAX_VALUE); // заполнить новый объект Integer[] случайными числами
        }
        map.put("second", new MathBox<>(b)); // добавить новый объект MathBox в HashMap
        for (String key : map.keySet()) {
            System.out.println(key + ' ' + map.get(key)); // вывести все пары ключ-значение из HashMap
        }
        /*
        5. Метод removeIfPresent(Integer) удаляет элемент из подлежащей коллекции, если он есть, и возвращает true,
        иначе оставляет коллекцию неизменной и возвращает false. При этом также выводится само значение, если оно было.
         */
        int attempts = args.length > 2 ? Integer.parseInt(args[2]) : 1 << 30; // количество попыток удаления из третьего арумента командной строки, иначе значение по умолчанию
        Random random = new Random();
        for (int attempt = 0; attempt < attempts; attempt++) {
            int nextInt = random.nextInt();
            if (mathBox.removeIfPresent(nextInt)) {
                System.out.println("Удалён элемент " + nextInt);
            }
        }
        System.out.println(mathBox); // вывод коллекции ещё раз, чтобы убедиться, что элементы удалились
    }
}
