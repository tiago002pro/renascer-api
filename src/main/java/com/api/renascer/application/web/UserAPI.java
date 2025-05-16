package com.api.renascer.application.web;

import com.api.renascer.domain.dto.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renascer-api/user")
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

    @PutMapping("/alter-password")
    public ResponseEntity alterPassword(@RequestBody @Validated ChangePassword data) {
        (service).alterPassword(data);
        return ResponseEntity.noContent().build();
    }
}
