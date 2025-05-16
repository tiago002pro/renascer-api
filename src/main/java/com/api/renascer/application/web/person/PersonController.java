package com.api.renascer.application.web.person;

import com.api.renascer.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonApi {

    @Override
    public ResponseEntity searchVideos(Person person) {
        return null;
    }
}
