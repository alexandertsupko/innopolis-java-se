/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example4.factory;

import ru.innopolis.homeworks.week4.homework17.example4.source_readers.SourceReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Базовый абстрактный класс.
 */
public abstract class Reader {
    protected abstract SourceReader createReader(String source);

    public String getContents(String source) {
        SourceReader reader = createReader(source);
        return new BufferedReader(new InputStreamReader(reader.getInputStream(source)))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
