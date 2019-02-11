/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example4.factory;

import ru.innopolis.assignments.week4.assignment17.example4.source_readers.SourceInputStream;
import ru.innopolis.assignments.week4.assignment17.example4.source_readers.URLInputStream;

public class URLReader extends SourceReader {
    @Override
    protected SourceInputStream createReader() {
        return new URLInputStream();
    }
}
