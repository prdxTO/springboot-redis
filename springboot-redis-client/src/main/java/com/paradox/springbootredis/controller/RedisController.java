package com.paradox.springbootredis.controller;

import com.paradox.springbootredis.model.Person;
import com.paradox.springbootredis.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RedisController {

    private PersonService personService;

    public RedisController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> personList() {
        Iterable<Person> allRecords = personService.findAll();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/personsByFirstName/{firstName}")
    public ResponseEntity<List<Person>> personsByFirstName(@PathVariable String firstName) {
        List<Person> personList = personService.findByFirstName(firstName);
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/personsByFirstNameReadOnly/{firstName}")
    public ResponseEntity<List<Person>> personsByFirstNameReadOnly(@PathVariable String firstName) {
        List<Person> personList = personService.findByFirstNameReadOnly(firstName);
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName == null) {
            return ResponseEntity.badRequest().build();
        }

        Person savedPerson = personService.addPerson(firstName, lastName);
        return ResponseEntity.ok(savedPerson);
    }

    @PostMapping("/flushCache")
    public void flushCache() {
        personService.flushCache();
    }
}
