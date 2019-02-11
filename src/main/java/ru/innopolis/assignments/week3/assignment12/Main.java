/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week3.assignment12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.assignments.week3.assignment12.dao.CourseDAO;
import ru.innopolis.assignments.week3.assignment12.dao.CourseDAOImpl;
import ru.innopolis.assignments.week3.assignment12.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            CourseDAO dao = new CourseDAOImpl(connection);

            Collection<Person> list = dao.getAllPersons();
            LOGGER.info("Start: {}", list);

            Person person = new Person();
            person.setName("John Doe");
            person.setBirthDate(System.currentTimeMillis());

            dao.createPerson(person);
            list = dao.getAllPersons();
            LOGGER.info("Create: {}", list);

            person.setName("O'Henry");

            dao.updatePerson(person);
            list = dao.getAllPersons();
            LOGGER.info("Update: {}", list);

            dao.deletePerson(person);
            list = dao.getAllPersons();
            LOGGER.info("Final: {}", list);
        } catch (SQLException e) {
            LOGGER.error("Error while getting connection to the database: ", e);
        }
    }
}
