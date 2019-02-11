/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment07;

/**
 * Вспомогательное перечисление, хранящее часто используемые строковые константы для путей и имён.
 *
 * @author Александр Цупко
 */
public enum PathsAndNamesConstants {
    FILE_SEPARATOR(System.getProperty("file.separator")),
    PACKAGE("ru.innopolis.assignments.week2.assignment07."),
    SIMPLE_NAME("SomeClass"),
    PATH("." + FILE_SEPARATOR.string + "target" + FILE_SEPARATOR.string + "classes" + FILE_SEPARATOR.string
            + "ru" + FILE_SEPARATOR.string + "innopolis" + FILE_SEPARATOR.string + "assignments" + FILE_SEPARATOR.string
            + "week2" + FILE_SEPARATOR.string + "assignment07" + FILE_SEPARATOR.string),
    FILE_NAME(SIMPLE_NAME.string + ".java"),
    FULLY_QUALIFIED_FILE_NAME(PATH.string + "" + FILE_NAME.string),
    BYTE_NAME(SIMPLE_NAME.string + ".class"),
    FULLY_QUALIFIED_BYTE_NAME(PATH.string + "" + BYTE_NAME.string);

    private String string;

    PathsAndNamesConstants(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
