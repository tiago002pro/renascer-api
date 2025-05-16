package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.model.Person;
import com.api.renascer.domain.repository.PersonRepository;
import com.api.renascer.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDomainService implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
