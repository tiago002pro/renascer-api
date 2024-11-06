package com.api.renascer.infrastructure.api.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.BookResponse;
import com.api.renascer.infrastructure.dto.abibliadigital.VersionsResponse;
import com.api.renascer.infrastructure.http.HttpRequestHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ABibliaDigitalApiAdapter implements ABibliaDigitalApiPort {

    private final HttpRequestHelper<BookResponse> httpRequestBooks;
    private final HttpRequestHelper<VersionsResponse> httpRequestVersions;

    @Value("${api-integration.a-biblia-digital.get-books.url}")
    private String urlBooks;

    @Value("${api-integration.a-biblia-digital.token}")
    private String token;

    public ResponseEntity<List<BookResponse>> getBooks() {
        return executeGet(urlBooks, token, httpRequestBooks, new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<List<VersionsResponse>> getVersions() {
        return executeGet(urlBooks, token, httpRequestVersions, new ParameterizedTypeReference<>() {});
    }

    private <T>ResponseEntity<List<T>> executeGet(
            String url,
            String token,
            HttpRequestHelper<T> httpRequestHelper,
            ParameterizedTypeReference<List<T>> responseType) {

        try {
            return httpRequestHelper.executeGetRequestWithReturnInList(url, token, null, null, responseType, null);
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }
}
