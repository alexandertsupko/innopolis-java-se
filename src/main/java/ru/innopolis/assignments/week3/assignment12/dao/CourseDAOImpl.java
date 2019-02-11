/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week3.assignment12.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.assignments.week3.assignment12.entity.Person;
import ru.innopolis.assignments.week3.assignment12.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CourseDAOImpl implements CourseDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAOImpl.class);

    private static final String INSERT_PERSON = "insert into person (name, birth_date) values (?, ?)";
    private static final String SELECT_PERSON = "select * from person";
    private static final String UPDATE_PERSON = "update person set name = ?, birth_date = ? where person_id = ?";
    private static final String DELETE_PERSON = "delete from person where name = ?";
    private static final String SELECT_PERSON_ID_BY_NAME = "select person_id from person where name = ?";

    private final Connection connection;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
        LOGGER.info("Connection has been injected successfully");
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
    public Collection<Person> getAllPersons() {
        Collection<Person> people = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON);
             ResultSet resultSet = statement.executeQuery()) {
            LOGGER.info("Database query successful");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                long birthDate = resultSet.getDate(3).getTime();
                people.add(new Person(id, name, birthDate));
            }
            LOGGER.info("List of people obtained successfully");
        } catch (SQLException e) {
            LOGGER.error("Error while retrieving data from the database: ", e);
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

    /**
     * Проверяет на <tt>null</tt> как сам входящий объект {@code Person}, так и его поле {@code name}.
     *
     * @param person объект для проверки на <tt>null</tt>
     * @return <tt>true</tt>, если и объект, и его поле не <tt>null</tt>, иначе <tt>false</tt>
     */
    private boolean isNull(Person person) {
        return person == null || person.getName() == null;
    }

    /**
     * Проверяет существование входящего объекта {@code Person} в базе данных на основе его поля {@code name}.
     *
     * @param person объект, существование которого нужно проверить
     * @return <tt>true</tt>, если объект с таким полем уже существует, иначе <tt>false</tt>
     */
    private boolean exists(Person person) {
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
    public void createPerson(Person person) {
        if (exists(person) || isNull(person)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error while preparing or executing the insert statement to the database: ", e);
        }
        setId(person); // newly created Person has 'id' field with a default value of 0, must be updated from the DB
    }

    /**
     * Устанавливает поле {@code id} переданного объекта {@code Person} по полю <tt>person_id</tt> из базы данных.
     *
     * @param person объект {@code Person}, значение поля {@code id} которого нужно взять из базы данных
     */
    private void setId(Person person) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_ID_BY_NAME)) {
            statement.setString(1, person.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    assert person.getId() == 0 : "Field 'id' was not 0 as expected";
                    int id = resultSet.getInt(1);
                    assert id > 0 : "Field 'person_id' must be positive";
                    person.setId(id);
                    LOGGER.info("Field 'id' of {} has changed successfully", person.getName());
                }
            } catch (SQLException e) {
                LOGGER.error("Error while executing query to the database: ", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while preparing statement for the query: ", e);
        }
    }

    @Override
    public void updatePerson(Person person) {
        if (exists(person) || isNull(person) || person.getId() <= 0) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.setInt(3, person.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error while preparing or executing the update statement to the database: ", e);
        }
    }

    @Override
    public void deletePerson(Person person) {
        if (!exists(person) && isNull(person)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            statement.setString(1, person.getName());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error while preparing or executing the delete statement to the database: ", e);
        }
    }

    @Override
    public void createSubject(Subject subject) {

    }
}
