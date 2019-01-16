package ru.inno.lec01;

public class HelloWorld {
    public static void main(String[] args) {
        Animal animal = new Dog();
    }
}

class Logger {
    Logger(String obj) {
        System.out.println("init" + obj);
    }
}

class Animal {
    static Logger staticName = new Logger("staticName");
    private Logger name = new Logger("privateName");
    private int age;

    static {
        System.out.println("static animal");
    }

    {
        System.out.println("animal");
    }

    public Animal() {
        System.out.println("constructor animal");
    }
}

class Dog extends Animal {
    static Logger dogName = new Logger("dogName");
    Logger instanceName = new Logger("instanceName");

    static {
        System.out.println("static dog");
    }

    {
        System.out.println("dog");
    }

    public Dog() {
        this("name");
        System.out.println("constructor dog");
    }

    public Dog(String name) {

    }
}