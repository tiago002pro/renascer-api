package com.api.renascer.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpResquestException extends RuntimeException {

    private HttpStatus httpStatus;

    public HttpResquestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
