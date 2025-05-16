package com.api.renascer.infrastructure.repository.person;

import com.api.renascer.domain.model.Person;
import com.api.renascer.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepository {

    private final PersonJPARepository personJPARepository;

    @Override
    public Person save(Person person) {
        return personJPARepository.save(person);
    }
}
