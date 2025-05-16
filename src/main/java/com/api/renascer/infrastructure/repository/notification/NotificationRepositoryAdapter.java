package com.api.renascer.infrastructure.repository.notification;

import com.api.renascer.domain.model.Notification;
import com.api.renascer.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final NotificationJPARepository notificationJPARepository;

    @Override
    public List<Notification> getAllNotifications(Long userId) {
        return notificationJPARepository.getAllNotifications(userId);
    }

    @Override
    public Boolean checkIfThereAreNotifications(Long userId) {
        return notificationJPARepository.checkIfThereAreNotifications(userId);
    }

    @Override
    public void insertNotification(String title, String description, Date date, String type, Long entityId) {
        notificationJPARepository.insertNotification(title, description, date, type, entityId);
    }

    @Override
    public void readAll(Long userId) {
        notificationJPARepository.readAll(userId);
    }

    @Override
    public void deleteAllByUser(Long userId) {
        notificationJPARepository.deleteAllByUser(userId);
    }
}
