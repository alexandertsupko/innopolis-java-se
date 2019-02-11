/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment09.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Конкретный буферизованный читатель из гиперссылки.
 *
 * @author Александр Цупко
 */
public class URLReader implements SourceReader {
    /**
     * Возвращает читателя гиперссылки, работающего с буферизованным потоком символов.
     *
     * @return буферизованный читатель гиперссылки
     */
    @Override
    public String getContents(String source) throws IOException {
        return new BufferedReader(new InputStreamReader(new URL(source).openStream()))
                .lines()
                .collect(Collectors.joining(" "));
    }
}
