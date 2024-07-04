package com.api.renascer.user.domain;

public record ChangePassword(String login, String password, String newPassword) {
}
