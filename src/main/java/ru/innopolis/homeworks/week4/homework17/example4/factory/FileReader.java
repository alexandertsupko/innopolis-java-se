/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example4.factory;

import ru.innopolis.homeworks.week4.homework17.example4.source_readers.FileInputStream;
import ru.innopolis.homeworks.week4.homework17.example4.source_readers.SourceInputStream;

public class FileReader extends SourceReader {
    @Override
    protected SourceInputStream createReader() {
        return new FileInputStream();
    }
}
