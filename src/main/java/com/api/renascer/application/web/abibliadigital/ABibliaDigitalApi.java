package com.api.renascer.application.web.abibliadigital;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/renascer-api/bible", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ABibliaDigitalApi {

    @GetMapping(value = "/books")
    ResponseEntity<List<BookResponse>> getBooks();
}
