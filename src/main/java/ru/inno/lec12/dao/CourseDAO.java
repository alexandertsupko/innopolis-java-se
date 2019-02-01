package ru.inno.lec12.dao;

import ru.inno.lec12.entity.Person;
import ru.inno.lec12.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {

    Collection<Subject> getAllSubjects();

    Collection<Subject> getSubjectsByPerson(Person person);

    Collection<Person> getAllPersons();

    Collection<Person> getPersonsBySubject(Subject subject);

    void linkToCourse(Person person, Subject subject);

    // todo: implement two more methods
//    void linkToCourse(Person person, Subject... subject);

//    void linkToCourse(Subject subject, Person... person);

    // todo: crud-operations for one entity should go into its own dao
    void createPerson(Person person) throws SQLException;

    void updatePerson(Person person);

    void deletePerson(Person person);

    void createSubject(Subject subject);
}
