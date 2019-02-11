/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment06;

import ru.innopolis.lectures.week2.lecture06.entity.Human;

/**
 * Вспомогательный класс для тестирования и отладки.
 *
 * @author Александр Цупко
 */
public class Main {
    // путь выходного файла
    private static final String PATH = "./target/classes/ru/innopolis/assignments/week2/assignment06/object.xml";

    /**
     * Тестирующий клиент.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // создание экземпяра сериализуемой сущности Human
        Human human = new Human();
        // вывод сущности Human
        System.out.println(human);
        // создание экземпляра тестируемого класса
        Serialization example = new Serialization();
        // вызов у экземпляра метода сериализации сущности Human в файл по пути PATH
        example.serialize(human, PATH);
        // вызов у экземпляра метода десериализации из файла по пути PATH в сущность Human
        human = (Human) example.deSerialize(PATH);
        // вывод сущности Human
        System.out.println(human);
    }
}
