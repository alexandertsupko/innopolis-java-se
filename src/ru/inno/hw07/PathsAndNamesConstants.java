package ru.inno.hw07;

/**
 * Вспомогательное перечисление, хранящее часто используемые строковые константы для путей и имён.
 *
 * @author Александр Цупко
 */
public enum PathsAndNamesConstants {
    FILE_SEPARATOR(System.getProperty("file.separator")),
    PACKAGE("ru.inno.hw07."),
    SIMPLE_NAME("SomeClass"),
    PATH("." + FILE_SEPARATOR.string + "out" + FILE_SEPARATOR.string + "production" + FILE_SEPARATOR.string
            + "untitled" + FILE_SEPARATOR.string + "ru" + FILE_SEPARATOR.string + "inno" + FILE_SEPARATOR.string
            + "hw07" + FILE_SEPARATOR.string),
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
