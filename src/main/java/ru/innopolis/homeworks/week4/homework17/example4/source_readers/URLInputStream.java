/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example4.source_readers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class URLInputStream implements SourceInputStream {
    @Override
    public BufferedInputStream getInputStream(String source) {
        try {
            return new BufferedInputStream(new URL(source).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
