package com.api.renascer.email;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/email")
public class EmailAPI {
    private final EmailService emailService;

    public EmailAPI(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/recover-password")
    public void sendEmail(@RequestBody Email email) {
        emailService.sendEmail(email);
    }

//    @PostMapping("/recover-password")
//    public void sendEmail2() throws UnsupportedEncodingException {
//        emailService.sendEmail2();
//    }
}
