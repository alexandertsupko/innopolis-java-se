/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example4.source_readers;

import java.io.BufferedInputStream;

public interface SourceInputStream {
    BufferedInputStream getInputStream(String source);
}
