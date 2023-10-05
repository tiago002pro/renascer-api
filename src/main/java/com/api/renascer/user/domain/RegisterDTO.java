package com.api.renascer.user.domain;

public record RegisterDTO(String login, String password, UserRole role) {
}
