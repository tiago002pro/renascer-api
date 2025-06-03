package com.api.renascer.domain.service.implementation;

import com.api.renascer.domain.dto.ChangePassword;
import com.api.renascer.domain.exception.HttpResquestException;
import com.api.renascer.domain.exception.NotFoundDatabaseException;
import com.api.renascer.domain.model.User;
import com.api.renascer.domain.repository.UserRepository;
import com.api.renascer.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDomainService implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final String NOT_FOUND_MESSAGE = "Nenhum reultado encontrado";

    @Override
    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User load(Long id) {

        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new NotFoundDatabaseException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (HttpResquestException e) {
            throw new HttpResquestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return "Deletado com sucesso!";
    }

    @Override
    public String alterPassword(ChangePassword changePassword) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(changePassword.login(), changePassword.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            String encryptedPassword = new BCryptPasswordEncoder().encode(changePassword.newPassword());
            Long userId = ((User) auth.getPrincipal()).getId();
            User user = load(userId);
            user.setPassword(encryptedPassword);

            userRepository.save(user);
            return "Alterado com sucesso!";
        } catch (Exception e) {
            throw new RuntimeException("Falha na autenticação: ".concat(e.getMessage()));
        }
    }

    @Override
    public List<User> findAllBirthdays() {
        return userRepository.findAllBirthdays();
    }

    @Override
    public List<String> findAllExpoToken() {
        return userRepository.findAllExpoToken();
    }
}
