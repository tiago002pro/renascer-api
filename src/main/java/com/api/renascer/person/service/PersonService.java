package com.api.renascer.person.service;

import com.api.renascer.person.model.Person;
import com.api.renascer.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Person save(Person person) {
        return this.repository.save(person);
    }
}
