package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.exception.HttpResquestException;
import com.api.renascer.domain.model.User;
import com.api.renascer.domain.record.Email;
import com.api.renascer.domain.record.SignInRequest;
import com.api.renascer.domain.record.SignUpRequest;
import com.api.renascer.domain.record.TokenLogin;
import com.api.renascer.domain.service.AuthenticationService;
import com.api.renascer.domain.service.EmailService;
import com.api.renascer.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationDomainService implements AuthenticationService {

    private final UserService userService;
    private final EmailService emailService;

    @Override
    public TokenLogin signIn(SignInRequest request) {
        return null;
    }

    @Override
    public String signUp(SignUpRequest request) {
        return "";
    }

    @Override
    public String checkEmail(String emailUser) {
        String code = this.generateCode();
        String text = "Obrigado por verificar a sua conta " + emailUser + "\n\n\n" + "O seu código é: " + code;
        Email email = new Email(emailUser, "Código de verificação Renascer App", text);

        try {
            emailService.sendEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("E-mail inválido: ".concat(e.getMessage()));
        }

        return code;
    }

    @Override
    public String recoverPassword(String emailUser) {
        UserDetails userDetails = userService.findByLogin(emailUser);

        if (userDetails != null) {
            User user = (User) userDetails;

            String password = this.generateNewPassword();
            user.setPassword(new BCryptPasswordEncoder().encode(password));

            String text = "Olá " + user.getName() + "\n\n\n" +
                    "Recebemos uma solicitação de redefinição de senha na plataforma da Igreja Renascer." +
                    "\nEsta é sua nova senha temporária" +
                    "\n\n" + password;

            Email email = new Email(emailUser, "Redefinição de senha Renascer App", text);

            this.emailService.sendEmail(email);
            userService.save(user);
            return "Sucesso ao redefinir o Senha";
        } else {
            throw new HttpResquestException("Usuário não econtrado", HttpStatus.NOT_FOUND);
        }
    }

    private String generateCode() {
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

    private String generateNewPassword() {
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
}
