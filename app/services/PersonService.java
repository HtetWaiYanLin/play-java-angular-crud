package services;

import models.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    void createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(int id);
}
