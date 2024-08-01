package com.api.renascer.user.controller;

import com.api.renascer.infra.security.TokenService;
import com.api.renascer.person.model.Person;
import com.api.renascer.user.LoginResponseDTO;
import com.api.renascer.user.domain.AuthenticationDTO;
import com.api.renascer.user.domain.RegisterDTO;
import com.api.renascer.user.domain.User;
import com.api.renascer.user.repository.UserRepository;
import com.api.renascer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/renascer-api/auth")
    public class AutheticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AutheticationController(AuthenticationManager authenticationManager,
                                   UserRepository repository,
                                   TokenService tokenService,
                                   UserService userService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Person person = new Person(data.name(), data.login());
        User newUser = new User(data.name(), data.login(), encryptedPassword, data.role(), data.expoToken(), person);

        return ResponseEntity.ok(this.repository.save(newUser));
    }

    @PostMapping("/check-email/{email}")
    public ResponseEntity checkEmail(@PathVariable String email) {
        return ResponseEntity.ok((userService).checkEmail(email));
    }

    @PostMapping("/recover-password/{email}")
    public void recoverPassword(@PathVariable String email) {
        (userService).recoverPassword(email);
        ResponseEntity.noContent().build();
    }
}
