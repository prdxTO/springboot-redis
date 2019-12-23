package com.paradox.springbootredisdataserver.repository;

import com.paradox.springbootredisdataserver.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAllByFirstName(String firstName);

    List<Person> findAllByLastName(String firstName);

}
