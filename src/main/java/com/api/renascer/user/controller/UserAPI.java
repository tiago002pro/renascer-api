package com.api.renascer.user.controller;

import com.api.renascer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAPI {
    private final UserService service;

    @Autowired
    public UserAPI(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity load(@PathVariable Long id) {
        return ResponseEntity.ok((service).loadById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        (service).delete(id);
        return ResponseEntity.noContent().build();
    }
}
