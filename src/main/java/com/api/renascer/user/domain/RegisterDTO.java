package com.api.renascer.user.domain;

public record RegisterDTO(String name, String login, String password, String phone, UserRole role) {
}
