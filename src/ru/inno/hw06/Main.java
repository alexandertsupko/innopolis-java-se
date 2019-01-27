package ru.inno.hw06;

import ru.inno.lec06.entity.Human;

/**
 * Вспомогательный класс для тестирования и отладки.
 *
 * @author Александр Цупко
 */
public class Main {
    private static final String PATH = "./src/ru/inno/hw06/object.xml"; // путь выходного файла

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
