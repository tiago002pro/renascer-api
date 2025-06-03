package com.api.renascer.application.web.user;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.dto.ChangePassword;
import com.api.renascer.domain.exception.ClientException;
import com.api.renascer.domain.model.User;
import com.api.renascer.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponse<User>> load(Long id) {
        try {
            ApiResponse<User> response = new ApiResponse<>(userService.load(id), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<User> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteById(Long id) {
        try {
            ApiResponse<String> response = new ApiResponse<>(userService.deleteById(id), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> alterPassword(ChangePassword changePassword) {
        try {
            ApiResponse<String> response = new ApiResponse<>(userService.alterPassword(changePassword), HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ClientException e) {
            ApiResponse<String> response = new ApiResponse<>(null, e.getHttpStatus().value(), e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }
}
