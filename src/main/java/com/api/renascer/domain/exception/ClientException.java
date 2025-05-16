package com.api.renascer.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException extends RuntimeException {

    private HttpStatus httpStatus;

    public ClientException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
