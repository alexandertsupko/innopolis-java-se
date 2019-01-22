package ru.inno.hw06;

public interface SerializableToFile {
    void serialize(Object object, String file);

    Object deSerialize(String file);
}
