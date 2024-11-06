package com.api.renascer.infrastructure.api.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.BookResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ABibliaDigitalServicePort {
    ResponseEntity<List<BookResponse>> getAllBooks();
}
