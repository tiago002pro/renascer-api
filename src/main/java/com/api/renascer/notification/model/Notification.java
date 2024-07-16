package com.api.renascer.notification.model;

import com.api.renascer.notification.helper.enums.NotificationTypes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Table(name = "notification")
@Entity(name = "notification")
@Data
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    @Column(name = "read")
    private Boolean read;
    @Column(name = "type", length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationTypes types;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "entity_id")
    private Long entityId;
}
