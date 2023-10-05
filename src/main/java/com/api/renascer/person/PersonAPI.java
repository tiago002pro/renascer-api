package com.api.renascer.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonAPI {
    private final PersonService service;

    @Autowired
    public PersonAPI(PersonService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Person person) {
        return ResponseEntity.ok((service).save(person));
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok((service).getAll());
    }
}
