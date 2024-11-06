package com.api.renascer.infrastructure.http;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class HttpRequestHelper<T> {

    private final RestTemplate restTemplate;

    public HttpRequestHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<T> executeGetRequest(
            String url,
            String token,
            String parameter,
            Map<String, String> queryParams,
            Class<T> responseType,
            Map<String, String> customHeader) {
        String urlComplete = buildUri(url, parameter, queryParams);
        return execute(HttpMethod.GET, urlComplete, token, Collections.emptyMap(), responseType, customHeader);
    }

    public ResponseEntity<List<T>> executeGetRequestWithReturnInList(
            String url,
            String token,
            String parameter,
            Map<String, String> queryParams,
            ParameterizedTypeReference<List<T>> responseType,
            Map<String, String> customHeader) {
        String urlComplete = buildUri(url, parameter, queryParams);
        return executeWithReturnInList(HttpMethod.GET, urlComplete, token, Collections.emptyMap(), responseType, customHeader);
    }

    private <R>ResponseEntity<R> execute(
            HttpMethod method,
            String url,
            String token,
            Map<String, Object> requestBody,
            Class<R> responseType,
            Map<String, String> customHeader) {

        HttpHeaders headers = buildHeaders(token, customHeader);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, method, entity, responseType);
    }

    private <R>ResponseEntity<List<R>> executeWithReturnInList(
            HttpMethod method,
            String url,
            String token,
            Map<String, Object> requestBody,
            ParameterizedTypeReference<List<R>> responseType,
            Map<String, String> customHeader) {

        HttpHeaders headers = buildHeaders(token, customHeader);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, method, entity, responseType);
    }

    private HttpHeaders buildHeaders(String token, Map<String, String> customHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        if (customHeader != null) {
            customHeader.forEach(headers::set);
        }

        return headers;
    }

    private String buildUri(String baseUrl, String parameter, Map<String, String> queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl).path(parameter);

        if (queryParams != null) {
            queryParams.forEach(uriBuilder::queryParam);
        }

        return uriBuilder.build().toString();
    }
}
