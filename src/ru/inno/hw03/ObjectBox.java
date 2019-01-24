package ru.inno.hw03;

import java.util.Collection;
import java.util.Collections;

/**
 * Инкапсулирует некоторую общую коллекцию элементов некоторого, заранее неизвестного типа,
 * и реализует типовые операции с такой коллекцией: добавление, удаление элементов и вывод содержимого.
 *
 * @param <T> обобщённый тип элементов, хранимых в подлежащей коллекции
 * @author Александр Цупко
 */
public class ObjectBox<T> {
    private Collection<T> collection = Collections.EMPTY_SET;

    /**
     * Добавляет в коллекцию переданный в аргументе объект.
     *
     * @param o объект, который нужно добавить
     * @return <tt>true</tt>, если объект был добавлен, и <tt>false</tt> в противном случае
     */
    public boolean addObject(T o) {
        return collection.add(o);
    }

    /**
     * Удаляет из коллекции переданный в аргументе объект.
     *
     * @param o объект, который нужно удалить
     * @return <tt>true</tt>, если объект был удалён, и <tt>false</tt> в противном случае
     */
    public boolean deleteObject(T o) {
        return collection.remove(o);
    }

    /**
     * Выводит содержимое коллекции в строку.
     *
     * @return строковое представление содержимого коллекции
     */
    public String dump() {
        StringBuilder contents = new StringBuilder("Содержимое: [ ");
        for (T o : collection) {
            contents.append(o).append(' ');
        }
        return contents.append(']').toString();
    }
}
