package com.api.renascer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpoNotificationDTO {
    private String expoToken;
    private String title;
    private String body;
}
