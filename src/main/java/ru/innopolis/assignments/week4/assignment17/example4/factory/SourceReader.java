/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example4.factory;

import ru.innopolis.assignments.week4.assignment17.example4.source_readers.SourceInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Базовый абстрактный класс.
 */
public abstract class SourceReader {
    protected abstract SourceInputStream createReader();

    public String getContents(String source) {
        SourceInputStream reader = createReader();
        return new BufferedReader(new InputStreamReader(reader.getInputStream(source)))
                .lines()
                .collect(Collectors.joining("%n"));
    }
}
