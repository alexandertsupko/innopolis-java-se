/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example4.source_readers;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

public class FileInputStream implements SourceInputStream {
    @Override
    public BufferedInputStream getInputStream(String source) {
        try {
            return new BufferedInputStream(new java.io.FileInputStream(source));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
