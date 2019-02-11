/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment09.factory;

import ru.innopolis.assignments.week2.assignment09.readers.SourceReader;

import java.io.IOException;

/**
 * Базовый абстрактный класс. Фабрика для создания конкретных читателей в зависимости от типа источника.
 *
 * @author Александр Цупко
 */
public abstract class SourceReaderFactory {
    /**
     * Абстрактный фабричный метод, переопределённый в подклассах.
     *
     * @return объект интерфейса {@code SourceReader}, созданный в одном из подклассов
     */
    protected abstract SourceReader createSourceReader();

    /**
     * Возвращает содержимое источника, прочитанное с помощью конкретного читателя,
     * обёрнутого в буферизованную оболочку. Символы новой строки заменены пробелом.
     *
     * @param source строковое представление названия источника (файл либо гиперссылка)
     * @return содержимое источника в виде объекта класса {@code String}
     */
    public String getContents(String source) throws IOException {
        return createSourceReader().getContents(source);
    }
}
