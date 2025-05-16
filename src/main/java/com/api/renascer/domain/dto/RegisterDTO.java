package com.api.renascer.domain.dto;

import com.api.renascer.domain.enums.UserRole;

public record RegisterDTO(String name, String login, String password, String phone, UserRole role, String expoToken) {
}
