package ru.inno.hw12.dao;

import ru.inno.hw12.entity.Person;
import ru.inno.hw12.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CourseDAOImpl implements CourseDAO {
    private static final String INSERT_PERSON = "insert into person (name, birth_date) values (?, ?)";
    private static final String SELECT_PERSON = "select * from person";
    private static final String UPDATE_PERSON = "update person set name = ?, birth_date = ? where person_id = ?";
    private static final String DELETE_PERSON = "delete from person where name = ?";
    private static final String SELECT_PERSON_ID_BY_NAME = "select person_id from person where name = ?";

    private final Connection connection;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Subject> getAllSubjects() {
        return null;
    }

    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) {
        return null;
    }

    @Override
    public Collection<Person> getAllPersons() throws SQLException {
        Collection<Person> people = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                long birthDate = resultSet.getDate(3).getTime();
                people.add(new Person(id, name, birthDate));
            }
        }
        return people;
    }

    @Override
    public Collection<Person> getPersonsBySubject(Subject subject) {
        return null;
    }

    @Override
    public void linkToCourse(Person person, Subject subject) {

    }

    private boolean isNull(Person person) {
        return person == null || person.getName() == null;
    }

    private boolean exists(Person person) throws SQLException {
        if (!isNull(person)) {
            for (Person p : this.getAllPersons()) {
                if (p.getName().equals(person.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void createPerson(Person person) throws SQLException {
        if (exists(person) || isNull(person)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_ID_BY_NAME)) {
            statement.setString(1, person.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    person.setId(resultSet.getInt(1));
                }
            }
        }
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        if (exists(person) || isNull(person) || person.getId() <= 0) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.setInt(3, person.getId());
            statement.execute();
        }
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        if (!exists(person) && isNull(person)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            statement.setString(1, person.getName());
            statement.execute();
        }

    }

    @Override
    public void createSubject(Subject subject) {

    }
}
