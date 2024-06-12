package com.api.renascer.notification.api;

import com.api.renascer.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationAPI {
    private final NotificationService service;

    @Autowired
    public NotificationAPI(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity getAllNotifications() {
        return ResponseEntity.ok((service).getAllNotifications());
    }

    @PutMapping("/read/{id}")
    public ResponseEntity readNotification(@PathVariable Long id) {
        return ResponseEntity.ok((service).readNotification(id));
    }
}
