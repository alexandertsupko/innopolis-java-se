/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework06;

/**
 * Интерфейс для инкапсуляции методов сериализации и десериализации.
 *
 * @author Александр Цупко
 */
public interface SerializableToFile {
    void serialize(Object object, String file);

    Object deSerialize(String file);
}
