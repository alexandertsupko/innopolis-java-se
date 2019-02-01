package ru.inno.lec04.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 17012019;

    public static String staticString = "static value";

    private String name;
    private int age;
    private double height;
    private boolean married;
    private transient String secretCode;

    public Person(String name, int age, double height, boolean married, String secretCode) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.married = married;
        this.secretCode = secretCode;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public boolean isMarried() {
        return married;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", married=" + married +
                ", secretCode=" + secretCode +
                '}';
    }
}
