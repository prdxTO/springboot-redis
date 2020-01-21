package com.paradox.springbootredis.service;

import com.paradox.springbootredis.model.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {

    private final String DATA_SERVICE_URL = "http://localhost:8082/";

    private RestTemplate restTemplate;

    public Person addPerson(String firstName, String lastName) {
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        HttpEntity<Person> request = new HttpEntity<>(person);
        return restTemplate.postForEntity(DATA_SERVICE_URL + "save", request, Person.class).getBody();
    }

    @Cacheable(value = "person")
    public List<Person> findAll() {
        log.info("called method findAll");
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForEntity(DATA_SERVICE_URL + "persons", Person[].class).getBody()));
    }

    @Cacheable(value = "person", key = "#firstName")
    public List<Person> findByFirstName(String firstName) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForEntity(DATA_SERVICE_URL + "personsByFirstName/" + firstName, Person[].class).getBody()));
    }

    @CacheEvict(value = "person", allEntries = true)
    public void flushCache() {
        log.info("Flushing Cache");
    }
}
