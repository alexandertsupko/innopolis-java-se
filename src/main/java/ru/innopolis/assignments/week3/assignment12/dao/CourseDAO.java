/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week3.assignment12.dao;

import ru.innopolis.assignments.week3.assignment12.entity.Person;
import ru.innopolis.assignments.week3.assignment12.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {

    Collection<Subject> getAllSubjects();

    Collection<Subject> getSubjectsByPerson(Person person);

    Collection<Person> getAllPersons() throws SQLException;

    Collection<Person> getPersonsBySubject(Subject subject);

    void linkToCourse(Person person, Subject subject);

    // todo: implement two more methods
//    void linkToCourse(Person person, Subject... subject);

//    void linkToCourse(Subject subject, Person... person);

    // todo: crud-operations for one entity should go into its own dao
    void createPerson(Person person) throws SQLException;

    void updatePerson(Person person) throws SQLException;

    void deletePerson(Person person) throws SQLException;

    void createSubject(Subject subject);
}
