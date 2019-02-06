package ru.innopolis.lectures.week3.lecture14;

import java.util.List;

public interface ResourceLoader extends AutoCloseable {
    List<String> getStringFromURL(String url);
}
