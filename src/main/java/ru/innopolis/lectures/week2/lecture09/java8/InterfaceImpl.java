package ru.innopolis.lectures.week2.lecture09.java8;

public class InterfaceImpl implements InterfaceA, InterfaceB {
    @Override
    public void doSome() {
//        ((InterfaceA)this).doSome();
        System.out.println("Impl");
    }


}
