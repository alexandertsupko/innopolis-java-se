/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework09.readers;

import java.io.*;

/**
 * Конкретный буферизованный читатель из файла.
 *
 * @author Александр Цупко
 */
public class FileReader implements SourceReader {
    /**
     * Возвращает читателя файла, работающего с буферизованным потоком символов.
     *
     * @param source источник, который требуется прочитать (файл или гиперссылка)
     * @return буферизованный читатель файла
     */
    @Override
    public BufferedReader getBufferedReader(String source) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(source)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
