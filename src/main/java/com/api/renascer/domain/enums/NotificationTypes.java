package com.api.renascer.domain.enums;

import lombok.Getter;

@Getter
public enum NotificationTypes {
    VIDEOS("VIDEOS", 1),
    EVENTOS("EVENTOS", 2),
    BIRTHDAY("BIRTHDAY", 3);

    private String type;
    private int value;

    NotificationTypes(String type, int value) {
        this.type = type;
        this.value = value;
    }
}
