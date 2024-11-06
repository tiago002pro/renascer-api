package com.api.renascer.infrastructure.api.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.BookResponse;
import com.api.renascer.infrastructure.dto.abibliadigital.VersionsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ABibliaDigitalApiPort {
    ResponseEntity<List<BookResponse>> getBooks();

    ResponseEntity<List<VersionsResponse>> getVersions();
}
