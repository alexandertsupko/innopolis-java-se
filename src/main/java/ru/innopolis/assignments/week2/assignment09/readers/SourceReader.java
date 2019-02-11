/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment09.readers;

import java.io.IOException;

/**
 * Интерфейс для различных видов читателей, использующих буферизованный поток символов.
 *
 * @author Александр Цупко
 */
@FunctionalInterface
public interface SourceReader {
    /**
     * Возвращает читателя, работающего с буферизованным потоком символов.
     *
     * @return буферизованный читатель источника
     */
    String getContents(String source) throws IOException;
}
