package ru.inno.lec14;

import java.util.List;

public interface ResourceLoader extends AutoCloseable {
    List<String> getStringFromURL(String url);
}
