package com.api.renascer.infrastructure.api.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ABibliaDigitalServiceAdapter implements ABibliaDigitalServicePort {

    private final ABibliaDigitalApiAdapter aBibliaDigitalApiAdapter;

    @Override
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return aBibliaDigitalApiAdapter.getBooks();
    }
}
