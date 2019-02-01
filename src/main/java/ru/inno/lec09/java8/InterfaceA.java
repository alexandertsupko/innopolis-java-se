package ru.inno.lec09.java8;

public interface InterfaceA {
    default void doSome() {
        System.out.println("A.doSome()");
    }
}
