package services;

import models.Person;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {

    private List<Person> persons = new ArrayList<>();
    private final IdGenerator idGenerator;
    @Inject
    public PersonServiceImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
        Person p = new Person();
        p.setId(idGenerator.getNextId());
        p.setFirstName("Lionel");
        p.setLastName("Messi");
        p.setAge(30);
        createPerson(p);

        p = new Person();
        p.setId(idGenerator.getNextId());
        p.setFirstName("Cristiano");
        p.setLastName("Ronaldo");
        p.setAge(32);
        createPerson(p);
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void createPerson(Person person) {
        if (person.getId() == 0) {
            person.setId(idGenerator.getNextId());
        }
        persons.add(person);
    }

    @Override
    public void updatePerson(Person person) {
        Person p = persons.stream().filter(x -> x.getId() == person.id).collect(Collectors.toList()).get(0);
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAge(person.age);
    }

    @Override
    public void deletePerson(int id) {
        persons = persons.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
    }
}
