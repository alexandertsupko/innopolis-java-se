package ru.innopolis.lectures.week2.lecture09.java8;

@java.lang.FunctionalInterface
public interface FunctionalInterface {
    void doSome();

    default void doDefault() {

    }

    int hashCode();

//    void doSomeElse();
}
