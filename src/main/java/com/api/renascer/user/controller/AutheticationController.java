package com.api.renascer.user.controller;

import com.api.renascer.infra.security.TokenService;
import com.api.renascer.person.model.Person;
import com.api.renascer.schedule.service.ScheduleService;
import com.api.renascer.user.LoginResponseDTO;
import com.api.renascer.user.domain.AuthenticationDTO;
import com.api.renascer.user.domain.RegisterDTO;
import com.api.renascer.user.domain.User;
import com.api.renascer.user.repository.UserRepository;
import com.api.renascer.user.service.UserService;
import com.api.renascer.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
    @RequestMapping("/auth")
    public class AutheticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;
    private final VideoService videoService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    @Autowired
    public AutheticationController(AuthenticationManager authenticationManager,
                                   UserRepository repository,
                                   TokenService tokenService,
                                   VideoService videoService,
                                   ScheduleService scheduleService,
                                   UserService userService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
        this.videoService = videoService;
        this.scheduleService = scheduleService;
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

    @GetMapping("/all-videos-by-category/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok((videoService).getAllByCategory(category));
    }

    @GetMapping("/all-schedule-valid-deadline")
    public ResponseEntity getAllByValidDeadline() {
        return ResponseEntity.ok((scheduleService).getAllByValidDeadline());
    }

    @PostMapping("/all-schedule/by-date")
    public ResponseEntity getByStartDate(@RequestParam("startDate") String startDate) throws ParseException {
        return ResponseEntity.ok((scheduleService).getByStartDate(startDate));
    }

    @PostMapping("/generate-schedule")
    public ResponseEntity getByStartDate(@RequestParam("month") String month,
                                         @RequestParam("year") String year) throws ParseException {
        return ResponseEntity.ok((scheduleService).generateSchedule(month, year));
    }

    @GetMapping("/latest-videos")
    public ResponseEntity getLatest() {
        return ResponseEntity.ok((videoService).getLatest());
    }

    @GetMapping("/search-videos")
    public ResponseEntity searchVideos(@RequestParam("search") String search) {
        return ResponseEntity.ok((videoService).searchVideos(search));
    }
}
