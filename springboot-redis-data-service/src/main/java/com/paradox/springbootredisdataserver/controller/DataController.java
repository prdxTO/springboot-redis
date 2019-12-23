package com.paradox.springbootredisdataserver.controller;

import com.paradox.springbootredisdataserver.model.Person;
import com.paradox.springbootredisdataserver.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class DataController {

    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> personList() {
        log.info("API hit for /persons");
        Iterable<Person> allRecords = personService.findAll();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/personsByFirstName/{firstName}")
    public ResponseEntity<List<Person>> personsByFirstName(@PathVariable String firstName) {
        log.info("API hit for /personsByFirstName");
        List<Person> personList = personService.findByFirstName(firstName);
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        log.info("API hit for /save");
        if (person == null) {
            return ResponseEntity.badRequest().build();
        }

        Person savedPerson = personService.addPerson(person);
        return ResponseEntity.ok(savedPerson);
    }
}
