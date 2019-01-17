package ru.inno.hw03;

import java.util.*;

/**
 * Домашняя работа №3 к теме "Collections Framework. Обобщённые типы".
 *
 * @author Александр Цупко
 */
public class MathBox {
    private Set<Integer> set = new TreeSet<>(); // элементы не должны повторяться и должны быть отсортированы

    public MathBox(Integer[] a) {
        set.addAll(Arrays.asList(a)); // 1. Элементы внутри конструктора раскладываются в подходящую коллекцию
    }

    /**
     * 2. Возвращает сумму всех элементов коллекции.
     *
     * @return сумма элементов коллекции, находящейся в переменной экземпляра
     */
    public Long summator() {
        long sum = 0;
        for (int element : set) {
            sum += element;
        }
        return sum;
    }

    /**
     * 3. Возвращает коллекцию вещественных чисел, каждое из которых является результатом деления
     * элементов коллекции, находящейся в переменной экземпляра, на переданный в качестве аргумента делитель.
     *
     * @param divisor вещественное число, на которое делится каждый элемент исходной коллекции
     * @return коллекция вещественных чисел, результатов от деления каждого элемента на переданный делитель
     */
    public Set<Double> splitter(Double divisor) {
        Set<Double> set = new TreeSet<>();
        for (int element : this.set) {
            set.add(element / divisor);
        }
        return set;
    }

    /**
     * 4. Переопределение метода {@code toString()}, создающее удобочитаемый вывод содержимого коллекции.
     *
     * @return строка, содержащее удобочитаемое представление содержимого коллекции
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Содержимое исходной коллекции: [ ");
        for (int i : set) {
            stringBuilder.append(i).append(' ');
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    /**
     * 4. Переопределение метода {@code equals(Object obj)}, проверяющее подлежащие коллекции на равенство элементов.
     *
     * @param o объект, с которым сравнивается на равенство текущий объект
     * @return <tt>true</tt>, если элементы в коллекциях равны, и <tt>false</tt> в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox that = (MathBox) o;
        return this.set.equals(that.set);
    }

    /**
     * 4. Переопределение метода {@code hashCode()}, сформированное стандартными средствами.
     *
     * @return значение типа {@code int}, использующееся для хранения объектов этого класса в подходящих коллекциях
     */
    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    /**
     * 5. Удаляет переданное в аргументе значение из коллекции, если оно там присутствует.
     *
     * @param value значение, которое нужно удалить из коллекции
     * @return  <tt>true</tt>, если элемент был в коллекции, и <tt>false</tt> в противном случае
     */
    public boolean removeIfPresent(Integer value) {
        return set.remove(value);
    }

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        /*
        Создание и инициализация входящего массива
         */
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10; // количество элементов во входящем массиве из первого арумента командной строки, иначе значение по умолчанию
        Integer[] a = new Integer[n]; // создание и инициализация пустого массива объектов класса Integer
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE); // заполнение случайными числами от 0 до 2^31-2
        }
        /*
        Проверка функциональных требований
         */
        MathBox mathBox = new MathBox(a); // 1. Массив Integer[] передаётся в конструктор (дубликаты не добавляются)
        System.out.println("Сумма чисел в коллекции: " + mathBox.summator()); // 2. Метод summator() выводит сумму элементов подлежащей коллекции
        System.out.println(mathBox); // вывод на экран исходной коллекции
        double divisor = args.length > 1 ? Double.parseDouble(args[1]) : 3.14; // делитель из второго аргумента командной строки, иначе значение по умолчанию
        /*
        3. Метод splitter(Double) создаёт новую коллекцию путём деления каждого элемента старой коллекции на переданный в аргументе делитель.
        Класс StringBuilder используется для эффективного создания строкового представления новой коллекции
         */
        StringBuilder stringBuilder = new StringBuilder("Содержимое после деления на " + divisor + ": [ ");
        for (double d : mathBox.splitter(divisor)) { // каждый элемент делится на переданный в аргументе делитель
            stringBuilder.append(String.format(Locale.US,"%.1f", d)).append(' ');
        }
        System.out.println(stringBuilder.append(']'));
        /*
        4. Проверка корректной работы методов equals() и hashCode() при хранении объектов этого класса в HashMap
         */
        Map<String, MathBox> map = new HashMap<>(); // создать HashMap для хранения объектов этого класса в качестве значений
        map.put("first", mathBox); // добавить текущий объект MathBox в HashMap
        Integer[] b = new Integer[n]; // создать новый объект Integer[]
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * Integer.MAX_VALUE); // заполнить новый объект Integer[] случайными числами
        }
        map.put("second", new MathBox(b)); // добавить новый объект MathBox в HashMap
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
