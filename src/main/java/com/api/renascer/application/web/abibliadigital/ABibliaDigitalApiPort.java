package com.api.renascer.application.web.abibliadigital;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ABibliaDigitalApiPort {
    ResponseEntity<List<BookResponse>> getBooks();

    ResponseEntity<List<VersionsResponse>> getVersions();
}
