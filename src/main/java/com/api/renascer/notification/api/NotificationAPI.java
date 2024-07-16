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
    public ResponseEntity getAllNotifications(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok((service).getAllNotifications(userId));
    }

    @PutMapping("/read/{id}")
    public ResponseEntity readNotification(@PathVariable Long id) {
        return ResponseEntity.ok((service).readNotification(id));
    }

    @GetMapping("/check-if-there-are-notifications")
    public ResponseEntity getNotifications(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok((service).checkIfThereAreNotifications(userId));
    }

    @PutMapping("/read-all/{userId}")
    public ResponseEntity readAllNotifications(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok((service).readAllNotifications(userId));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity deleteAllNotifications(@RequestParam(name = "userId") Long userId) {
        (service).deleteAllNotifications(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/expo")
    public ResponseEntity expoNotification(@RequestParam("token") String expoToken) {
        return ResponseEntity.ok((service).expoNotification(expoToken));
    }

    @PostMapping("/generate")
    public ResponseEntity generate() {
        return ResponseEntity.ok((service).generate());
    }
}
