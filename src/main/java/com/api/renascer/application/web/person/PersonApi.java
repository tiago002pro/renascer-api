package com.api.renascer.application.web.person;

import com.api.renascer.domain.model.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PersonApi {

    @PutMapping("/update")
    ResponseEntity searchVideos(@RequestBody Person person);
}
