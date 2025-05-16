package com.api.renascer.infrastructure.repository.notification;

import com.api.renascer.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationJPARepository extends JpaRepository<Notification, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM notification n WHERE n.user_id = :userId ORDER BY n.id DESC")
    List<Notification> getAllNotifications(@Param("userId") Long userId);

    @Query(nativeQuery = true,
            value = "SELECT count(*) > 0 FROM notification n WHERE n.user_id = :userId AND n.read IS FALSE")
    Boolean checkIfThereAreNotifications(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = " INSERT INTO notification (title, description, date, read, type, user_id, entity_id) " +
                    " SELECT " +
                    "   :title, " +
                    "   :description, " +
                    "   :date, " +
                    "   false, " +
                    "   :type, " +
                    "   u.id," +
                    "   :entityId " +
                    " FROM users u ")
    void insertNotification(@Param("title") String title,
                            @Param("description") String description,
                            @Param("date") Date date,
                            @Param("type") String type,
                            @Param("entityId") Long entityId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = " UPDATE notification " +
                    " SET read = TRUE " +
                    " WHERE user_id = :userId ")
    void readAll(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = " DELETE FROM notification " +
                    " WHERE user_id = :userId " +
                    "   AND read IS TRUE")
    void deleteAllByUser(@Param("userId") Long userId);
}
