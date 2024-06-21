package com.api.renascer.notification.repository;

import com.api.renascer.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM notification n ORDER BY n.id DESC")
    List<Notification> getAllNotifications();

    @Query(nativeQuery = true,
            value = "SELECT count(*) > 0  FROM notification n WHERE n.read IS FALSE")
    Boolean checkIfThereAreNotifications();
}
