/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example4.source_readers;

import java.io.BufferedInputStream;

public interface SourceReader {
    BufferedInputStream getInputStream(String source);
}
