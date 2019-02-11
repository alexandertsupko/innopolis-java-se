/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment09.readers;

import java.io.*;
import java.util.stream.Collectors;

/**
 * Конкретный буферизованный читатель из файла.
 *
 * @author Александр Цупко
 */
public class FileReader implements SourceReader {
    /**
     * Возвращает читателя файла, работающего с буферизованным потоком символов.
     *
     * @return буферизованный читатель файла
     */
    @Override
    public String getContents(String source) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(source)))
                .lines()
                .collect(Collectors.joining(" "));
    }
}
