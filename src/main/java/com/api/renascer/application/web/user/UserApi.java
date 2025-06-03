package com.api.renascer.application.web.user;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.dto.ChangePassword;
import com.api.renascer.domain.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<User>> load(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id);

    @PutMapping("/alter-password")
    ResponseEntity<ApiResponse<String>> alterPassword(@RequestBody @Validated ChangePassword data);
}
