package com.api.renascer.user.service;

import com.api.renascer.email.Email;
import com.api.renascer.email.EmailService;
import com.api.renascer.user.domain.User;
import com.api.renascer.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    private final UserRepository repository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public User loadById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public String generateNewPassword() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
        Random random = new Random();

        String newPassword = "";
        int index = -1;
        for (int i = 0; i < 5; i ++) {
            index = random.nextInt(letras.length());
            newPassword += letras.substring(index, index + 1);
        }

        return newPassword;
    }

    public void recoverPassword(String emailUser) {
        User user = this.repository.findByEmail(emailUser);

        String password = this.generateNewPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        String text = "Olá " + user.getName() + "\n\n\n" +
                "Recebemos uma solicitação de redefinição de senha na plataforma da Igreja Renascer." +
                "\nEsta é sua nova senha temporária" +
                "\n\n" + password;

        Email email = new Email(emailUser, "Redefinição de senha", text);

        this.emailService.sendEmail(email);
        this.repository.save(user);
    }
}
