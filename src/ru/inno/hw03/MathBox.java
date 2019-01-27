package ru.inno.hw03;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Домашняя работа №3 к теме "Collections Framework. Обобщённые типы".
 *
 * @param <T> обобщённый тип элементов, хранимых в подлежащей коллекции, который является подклассом {@code Number}
 * @author Александр Цупко
 */
public class MathBox<T extends Number & Comparable> extends ObjectBox<T> {
    private Set<T> set = new TreeSet<>(); // элементы не должны повторяться и должны быть отсортированы
    // формула вычисления хэш-кода даёт значения в диапазоне [-2 ^ 31 + 1 .. 2 ^ 31 - 1]
    private final int HASH_CODE = (int) (Math.random() * Integer.MIN_VALUE * (Math.random() < 0.5 ? -1 : 1));

    public MathBox(T[] a) {
        set.addAll(Arrays.asList(a)); // 1. Элементы внутри конструктора раскладываются в подходящую коллекцию
    }

    /**
     * 2. Возвращает сумму всех элементов коллекции.
     *
     * @return сумма элементов коллекции, находящейся в переменной экземпляра
     */
    public T summator() {
        Double sum = 0d;
        for (T element : this.set) {
            sum += element.doubleValue();
        }
        return (T) sum;
    }

    /**
     * 3. Возвращает коллекцию вещественных чисел, каждое из которых является результатом деления
     * элементов коллекции, находящейся в переменной экземпляра, на переданный в качестве аргумента делитель.
     *
     * @param divisor вещественное число, на которое делится каждый элемент исходной коллекции
     * @return коллекция вещественных чисел, результатов от деления каждого элемента на переданный делитель
     */
    public Set<? extends Number> splitter(Double divisor) {
        Set<BigDecimal> set = new TreeSet<>();
        for (T element : this.set) {
            set.add(BigDecimal.valueOf(element.doubleValue() / divisor));
        }
        return set;
    }

    /**
     * 4. Переопределение метода {@code toString()}, создающее удобочитаемый вывод содержимого коллекции.
     *
     * @return строка, содержащая удобочитаемое представление содержимого коллекции
     */
    @Override
    public String toString() {
        StringBuilder contents = new StringBuilder("Содержимое исходной коллекции: [ ");
        for (T element : this.set) {
            contents.append(element).append(' ');
        }
        contents.append(']');
        return contents.toString();
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
     * 4. Переопределение метода {@code hashCode()}, согласующееся с переопределением {@code equals(Object obj)}.
     *
     * @return значение типа {@code int}, использующееся для хранения объектов этого класса в подходящих коллекциях
     */
    @Override
    public int hashCode() {
        return HASH_CODE; // мы не можем основывать вычисление хэш-кода на коллекции
    }

    /**
     * 5. Удаляет переданное в аргументе значение из коллекции, если оно там присутствует.
     *
     * @param value значение, которое нужно удалить из коллекции
     * @return  <tt>true</tt>, если элемент был в коллекции, и <tt>false</tt> в противном случае
     */
    public boolean removeIfPresent(T value) {
        return set.remove(value);
    }
}
