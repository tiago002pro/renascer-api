package com.api.renascer.infrastructure.repository.person;

import com.api.renascer.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJPARepository extends JpaRepository<Person, Long> {
}
