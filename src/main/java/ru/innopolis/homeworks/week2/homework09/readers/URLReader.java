/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09.readers;

import java.io.*;
import java.net.URL;

/**
 * Конкретный буферизованный читатель из гиперссылки.
 *
 * @author Александр Цупко
 */
public class URLReader implements SourceReader {
    /**
     * Возвращает читателя гиперссылки, работающего с буферизованным потоком символов.
     *
     * @param source источник, который требуется прочитать (файл или гиперссылка)
     * @return буферизованный читатель гиперссылки
     */
    @Override
    public BufferedReader getBufferedReader(String source) {
        try {
            return new BufferedReader(new InputStreamReader(new URL(source).openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
