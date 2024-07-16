package com.api.renascer.notification.helper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpoNotificationDTO {
    private String expoToken;
    private String title;
    private String body;
}
