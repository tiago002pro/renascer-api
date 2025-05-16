package com.api.renascer.application.web.abibliadigital;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ABibliaDigitalServicePort {
    ResponseEntity<List<BookResponse>> getAllBooks();
}
