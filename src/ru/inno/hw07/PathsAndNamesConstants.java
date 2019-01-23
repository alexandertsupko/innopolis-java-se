package ru.inno.hw07;

public enum PathsAndNamesConstants {
    PACKAGE("ru.inno.hw07"),
    SIMPLE_NAME("SomeClass"),
    FULLY_QUALIFIED_NAME(PACKAGE.string + "." + SIMPLE_NAME.string),
    PATH("./src/ru/inno/hw07/"),
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
