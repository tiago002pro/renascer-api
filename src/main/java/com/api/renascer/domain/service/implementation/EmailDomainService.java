package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.record.Email;
import com.api.renascer.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailDomainService implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(Email email) {
        var message = new SimpleMailMessage();

        message.setFrom("naoresponda.renascer@gmail.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());
        mailSender.send(message);
    }
}
