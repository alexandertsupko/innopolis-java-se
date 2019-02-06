package ru.innopolis.lectures.week1.lecture04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ErrorInputStreamReader extends FileInputStream {
    public ErrorInputStreamReader(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public void close() throws IOException { super.close();
        throw new IOException("Ошибка в методе close()");
    }
}
