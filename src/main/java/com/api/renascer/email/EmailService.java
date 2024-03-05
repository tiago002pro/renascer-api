package com.api.renascer.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Email email) {
        var message = new SimpleMailMessage();

        String text = "Olá Fulano \n\n\n" +
                "Recebemos uma solicitação de redefinição de senha na plataforma da Igreja Renascer." +
                "\nEsta é sua nova senha temporária" +
                "\n\n1 2 3 4 5 6 7 8";

        message.setFrom("naoresponda@igrejarenascer.org.br");
        message.setTo(email.to());
        message.setSubject("Redefinição de senha");
        message.setText(text);
        .        mailSender.send(message);
    }

    public void sendEmail2() throws UnsupportedEncodingException {
        Properties pros = new Properties();
        Session session = Session.getDefaultInstance(pros, null);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("tiagobarbosa02@outlook.com", "Mr. User"));
            message.setSubject("Your Example.com account has been activated");
            message.setText("This is a test");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
