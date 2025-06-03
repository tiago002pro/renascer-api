package com.api.renascer.domain.service;

import com.api.renascer.domain.model.Notification;
import com.api.renascer.domain.dto.ExpoNotificationDTO;

import java.util.List;

public interface NotificationService {

    List<Notification> getAllNotifications(Long userId);

    Boolean checkIfThereAreNotifications(Long userId);

    Notification readNotification(Long id);

    List<Notification> readAllNotifications(Long userId);

    String deleteAllNotifications(Long userId);

    String sendExpoNotification(ExpoNotificationDTO dto);

    String expoNotification(String expoToken);

    String generate();
}
