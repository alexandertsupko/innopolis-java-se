/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09.factory;

import ru.innopolis.homeworks.week2.homework09.readers.FileReader;
import ru.innopolis.homeworks.week2.homework09.readers.SourceReader;

/**
 * Конкретный класс-наследник класса {@code SourceReaderFactory}, создающий читателя из файла.
 *
 * @author Александр Цупко
 */
public class FileReaderFactory extends SourceReaderFactory {
    /**
     * Возвращает читателя из файла.
     *
     * @return объект интерфейса {@code SourceReader}, имеющий метод для получения буферизованного читателя
     */
    @Override
    protected SourceReader createSourceReader() {
        return new FileReader();
    }
}
