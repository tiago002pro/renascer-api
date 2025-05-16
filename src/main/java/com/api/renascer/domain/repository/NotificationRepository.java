package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.Notification;

import java.util.Date;
import java.util.List;

public interface NotificationRepository {

    List<Notification> getAllNotifications(Long userId);

    Boolean checkIfThereAreNotifications(Long userId);

    void insertNotification(String title,
                            String description,
                            Date date,
                            String type,
                            Long entityId);

    void readAll(Long userId);

    void deleteAllByUser(Long userId);
}
