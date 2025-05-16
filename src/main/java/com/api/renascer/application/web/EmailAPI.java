package com.api.renascer.application.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailAPI {
    private final EmailService emailService;

    public EmailAPI(EmailService emailService) {
        this.emailService = emailService;
    }
}
