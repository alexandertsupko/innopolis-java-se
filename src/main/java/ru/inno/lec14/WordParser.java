package ru.inno.lec14;

import java.util.List;
import java.util.NoSuchElementException;

public class WordParser implements AutoCloseable {
    private final ResourceLoader loader;

    public WordParser(ResourceLoader loader) {
        this.loader = loader;
    }

    public String firstWord(String url) {
        List<String> list = loader.getStringFromURL(url);
        if (list == null) {
            throw new NoSuchElementException();
        }
        for (String s : list) {
            if (s.trim().isEmpty()) {
                continue;
            }
            String[] words = s.split(" ");
            if (words.length > 0) {
                return words[0];
            }
        }
        throw new NoSuchElementException();
    }


    @Override
    public void close() throws Exception {
        loader.close();
    }
}
