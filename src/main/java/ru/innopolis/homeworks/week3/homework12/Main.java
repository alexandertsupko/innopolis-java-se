/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week3.homework12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.homeworks.week3.homework12.dao.CourseDAO;
import ru.innopolis.homeworks.week3.homework12.dao.CourseDAOImpl;
import ru.innopolis.homeworks.week3.homework12.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            CourseDAO dao = new CourseDAOImpl(connection);

            System.out.println("Start: " + dao.getAllPersons());

            Person person = new Person();
            person.setName("John Doe");
            person.setBirthDate(System.currentTimeMillis());

            dao.createPerson(person);
            System.out.println("Create: " + dao.getAllPersons());

            person.setName("O'Henry");

            dao.updatePerson(person);
            System.out.println("Update: " + dao.getAllPersons());

            dao.deletePerson(person);
            System.out.println("Final: " + dao.getAllPersons());
        } catch (SQLException e) {
            LOGGER.error("Error while getting connection to the database: " + e.getMessage());
        }
    }
}
