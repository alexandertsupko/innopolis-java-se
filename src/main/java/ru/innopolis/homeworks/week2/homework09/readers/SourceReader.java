/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09.readers;

import java.io.BufferedReader;

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
     * @param source источник, который требуется прочитать (файл или гиперссылка)
     * @return буферизованный читатель источника
     */
    BufferedReader getBufferedReader(String source);
}
