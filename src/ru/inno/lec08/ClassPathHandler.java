package ru.inno.lec08;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ClassPathHandler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        URL url = ClassPathHandler.class.getClassLoader().getResource(u.getHost());
        return url == null ? null : url.openConnection();
    }
}
