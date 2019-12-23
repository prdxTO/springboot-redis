package com.paradox.springbootredisdataserver.service;

import com.paradox.springbootredisdataserver.model.Person;
import com.paradox.springbootredisdataserver.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public List<Person> findByFirstName(String firstName) {
        return personRepository.findAllByFirstName(firstName);
    }
}
