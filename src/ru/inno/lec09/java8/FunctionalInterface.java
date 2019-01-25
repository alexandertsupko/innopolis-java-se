package ru.inno.lec09.java8;

@java.lang.FunctionalInterface
public interface FunctionalInterface {
    void doSome();

    default void doDefault() {

    }

    int hashCode();

//    void doSomeElse();
}
