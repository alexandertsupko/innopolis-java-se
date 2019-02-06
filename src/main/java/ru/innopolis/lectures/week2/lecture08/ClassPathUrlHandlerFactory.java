package ru.innopolis.lectures.week2.lecture08;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class ClassPathUrlHandlerFactory implements URLStreamHandlerFactory {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("classpath".equals(protocol)) {
            return new ClassPathHandler();
        }
        return null;
    }
}
