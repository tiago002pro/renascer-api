package com.api.renascer.user.service;

import com.api.renascer.email.Email;
import com.api.renascer.email.EmailService;
import com.api.renascer.user.domain.ChangePassword;
import com.api.renascer.user.domain.User;
import com.api.renascer.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private final UserRepository repository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository repository,
                       EmailService emailService,
                       AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    public User loadById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public String generateCode() {
        String numbers = "0123456789";
        Random random = new Random();

        String code = "";
        int index = -1;
        for (int i = 0; i < 4; i ++) {
            index = random.nextInt(numbers.length());
            code += numbers.substring(index, index + 1);
        }

        return code;
    }

    public String checkEmail(String emailUser) {
        String code = this.generateCode();
        String text = "Obrigado por verificar a sua conta " + emailUser + "\n\n\n" + "O seu código é: " + code;
        Email email = new Email(emailUser, "Código de verificação Renascer App", text);

        try {
            this.emailService.sendEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("E-mail inválido: ".concat(e.getMessage()));
        }

        return code;
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

        Email email = new Email(emailUser, "Redefinição de senha Renascer App", text);

        this.emailService.sendEmail(email);
        this.repository.save(user);
    }

    public void alterPassword(ChangePassword data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.newPassword());
            Long userId = ((User) auth.getPrincipal()).getId();
            Optional<User> userOptional = this.repository.findById(userId);
            User user = userOptional.get();
            user.setPassword(encryptedPassword);

            this.repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Falha na autenticação: ".concat(e.getMessage()));
        }
    }
}
