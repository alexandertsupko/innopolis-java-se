package ru.innopolis.lectures.week3.lecture12;

import ru.innopolis.lectures.week3.lecture12.dao.CourseDAO;
import ru.innopolis.lectures.week3.lecture12.dao.CourseDAOImpl;
import ru.innopolis.lectures.week3.lecture12.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String pass = "";

        Connection connection = DriverManager.getConnection(url, login, pass);
        System.out.println(connection);

        CourseDAO dao = new CourseDAOImpl(connection);

        Person person = new Person();
        person.setName("John Smith");
        person.setBirthDate(System.currentTimeMillis());
        dao.createPerson(person);

        // don't forget to close connection
    }
}
