package com.api.renascer.person.api;

import com.api.renascer.person.model.Person;
import com.api.renascer.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renascer-api/person")
public class PersonAPI {
    private final PersonService service;

    @Autowired
    public PersonAPI(PersonService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Person person,
                                 @PathVariable Long id) {
        return ResponseEntity.ok((service).save(person));
    }
}
