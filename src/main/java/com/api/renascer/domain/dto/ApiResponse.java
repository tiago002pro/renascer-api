package com.api.renascer.domain.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final T result;
    private final int statusCode;
    private String errorMessage;

    public ApiResponse(T result, int statusCode) {
        this.result = result;
        this.statusCode = statusCode;
    }

    public ApiResponse(T result, int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.result = result;
        this.errorMessage = errorMessage;
    }
}
