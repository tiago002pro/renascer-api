package com.api.renascer.application.web.notification;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.exception.ClientException;
import com.api.renascer.domain.model.Notification;
import com.api.renascer.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<ApiResponse<Notification>> readNotification(Long id) {
        try {
            ApiResponse<Notification> response = new ApiResponse<>(notificationService.readNotification(id), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<Notification> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Notification>>> getNotifications(Long userId) {
        try {
            ApiResponse<List<Notification>> response = new ApiResponse<>(notificationService.getAllNotifications(userId), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<List<Notification>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Notification>>> readAllNotifications(Long userId) {
        try {
            ApiResponse<List<Notification>> response = new ApiResponse<>(notificationService.readAllNotifications(userId), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<List<Notification>> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteAllNotifications(Long userId) {
        try {
            ApiResponse<String> response = new ApiResponse<>(notificationService.deleteAllNotifications(userId), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> expoNotification(String expoToken) {
        try {
            ApiResponse<String> response = new ApiResponse<>(notificationService.expoNotification(expoToken), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> generate() {
        try {
            ApiResponse<String> response = new ApiResponse<>(notificationService.generate(), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }
}
