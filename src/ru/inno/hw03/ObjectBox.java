package ru.inno.hw03;

import java.util.Collection;

public class ObjectBox {
    private Collection<Object> collection;

    public ObjectBox() {
    }

    public boolean addObject(Object o) {
        return collection.add(o);
    }

    public boolean deleteObject(Object o) {
        return collection.remove(o);
    }

    public String dump() {
        StringBuilder stringBuilder = new StringBuilder("Contents: [ ");
        for (Object o : collection) {
            stringBuilder.append(o).append(' ');
        }
        return stringBuilder.append(']').toString();
    }
}
