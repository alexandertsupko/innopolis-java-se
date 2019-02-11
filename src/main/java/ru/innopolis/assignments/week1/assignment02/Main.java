/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week1.assignment02;

import java.util.Random;

public class Main {
    private static Random random = new Random(); // генератор псевдослучайных чисел

    /**
     * Метод {@code main(String[])} получает из командной строки количество элементов в массиве,
     * создаёт массив объектов класса {@code Integer} и заполняет его псевдослучайными числами с помощью
     * генератора псевдослучайных чисел, объявленного как переменная класса, производит автоматическую обёртку,
     * затем сортирует этот массив алгоритмом сортировки вставками и выводит его содержимое до и после.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 1 << 3; // количество элементов в массиве передаётся из командной строки, по умолчанию 8
        Integer[] a = new Integer[n]; // объявление и создание массива объектов класса Integer
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(); // заполнение массива случайными числами из диапазона типа int
        }
        InsertionSort insertionSort = new InsertionSort();
        System.out.println("Содержимое массива до сортировки: ");
        insertionSort.print(a); // вывод до сортировки
        insertionSort.sort(a); // сортировка массива
        System.out.println("Содержимое массива после сортировки: ");
        insertionSort.print(a); // вывод после сортировки
    }
}
