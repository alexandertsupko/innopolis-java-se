package ru.inno.hw12;

import ru.inno.hw12.dao.CourseDAO;
import ru.inno.hw12.dao.CourseDAOImpl;
import ru.inno.hw12.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
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
        }
    }
}
