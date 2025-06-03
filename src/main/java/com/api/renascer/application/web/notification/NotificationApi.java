package com.api.renascer.application.web.notification;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.model.Notification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public interface NotificationApi {

    @PutMapping("/read/{id}")
    ResponseEntity<ApiResponse<Notification>> readNotification(@PathVariable Long id);

    @GetMapping("/check-if-there-are-notifications")
    ResponseEntity<ApiResponse<List<Notification>>> getNotifications(@RequestParam(name = "userId") Long userId);

    @PutMapping("/read-all/{userId}")
    ResponseEntity<ApiResponse<List<Notification>>> readAllNotifications(@PathVariable(name = "userId") Long userId);

    @DeleteMapping("/delete-all")
    ResponseEntity<ApiResponse<String>> deleteAllNotifications(@RequestParam(name = "userId") Long userId);

    @PostMapping("/expo")
    ResponseEntity<ApiResponse<String>> expoNotification(@RequestParam("token") String expoToken);

    @PostMapping("/generate")
    ResponseEntity<ApiResponse<String>> generate();
}
