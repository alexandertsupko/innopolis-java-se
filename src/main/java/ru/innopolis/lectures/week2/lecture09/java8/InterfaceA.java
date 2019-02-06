package ru.innopolis.lectures.week2.lecture09.java8;

public interface InterfaceA {
    default void doSome() {
        System.out.println("A.doSome()");
    }
}
