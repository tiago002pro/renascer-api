package com.api.renascer.domain.model;

import com.api.renascer.domain.enums.NotificationTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "notification")
@Entity(name = "notification")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
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
