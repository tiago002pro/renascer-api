package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.Person;

public interface PersonRepository {
    Person save(Person person);
}
