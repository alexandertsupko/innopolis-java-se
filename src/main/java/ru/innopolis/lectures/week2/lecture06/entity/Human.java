package ru.innopolis.lectures.week2.lecture06.entity;

@HumanAnnotation(name = "Строитель")
public class Human {
    private static String human = "Гуманоид";
    private String name = "Вася";
    private final int money = 100;

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", money=" + money +
                "}, human=" + human;
    }
}
