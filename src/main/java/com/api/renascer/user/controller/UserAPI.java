package com.api.renascer.user.controller;

import com.api.renascer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
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
}
