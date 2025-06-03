package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.Notification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

    Optional<Notification> findById(Long id);

    Notification save(Notification notification);

    List<Notification> saveAll(List<Notification> notifications);

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
