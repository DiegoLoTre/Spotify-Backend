package com.dlt.spotify.services;

import com.dlt.spotify.models.Person;
import com.dlt.spotify.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

}
