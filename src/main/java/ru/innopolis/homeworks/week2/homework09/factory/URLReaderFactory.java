/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09.factory;

import ru.innopolis.homeworks.week2.homework09.readers.SourceReader;
import ru.innopolis.homeworks.week2.homework09.readers.URLReader;

/**
 * Конкретный класс-наследник класса {@code SourceReaderFactory}, создающий читателя из гиперссылки.
 *
 * @author Александр Цупко
 */
public class URLReaderFactory extends SourceReaderFactory {
    /**
     * Возвращает читателя из гиперссылки.
     *
     * @return объект интерфейса {@code SourceReader}, имеющий метод для получения буферизованного читателя
     */
    @Override
    protected SourceReader createSourceReader() {
        return new URLReader();
    }
}
