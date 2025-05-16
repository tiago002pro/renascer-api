package com.api.renascer.application.web.abibliadigital;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ABibliaDigitalController implements ABibliaDigitalApi {

    private final ABibliaDigitalServicePort aBibliaDigitalServicePort;

    @Override
    public ResponseEntity<List<BookResponse>> getBooks() {
        return aBibliaDigitalServicePort.getAllBooks();
    }
}
