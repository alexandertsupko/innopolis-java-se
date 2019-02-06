package ru.innopolis.lectures.week3.lecture14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordParserTest {
    private WordParser parser;
    private ResourceLoader loader;

    @BeforeEach
    void setUp() {
        // variant 1
        loader = new ResourceLoader() {
            @Override
            public List<String> getStringFromURL(String url) {
                if ("1".equals(url)) {
                    return null;
                }
                return Collections.emptyList();
            }

            @Override
            public void close() throws Exception {

            }
        };
        parser = new WordParser(loader);

        // variant 2
        loader = Mockito.mock(ResourceLoader.class);
        parser = new WordParser(loader);
    }

    @AfterEach
    void tearDown() throws Exception {
        parser.close();
        Mockito.verify(loader, Mockito.times(1)).close();
    }

    @Test
    void firstWord() throws Exception {
        Mockito.when(loader.getStringFromURL(Mockito.anyString()))
                .thenReturn(Arrays.asList("jam"));
        Mockito.when(loader.getStringFromURL("1"))
                .thenReturn(Arrays.asList("","","word1"));

        assertEquals("word1", parser.firstWord("1"));

        Mockito.when(loader.getStringFromURL(Mockito.anyString()))
                .thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> parser.firstWord(""));
    }
}