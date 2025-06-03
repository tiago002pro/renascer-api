package com.api.renascer.infrastructure.repository.user;

import com.api.renascer.domain.model.User;
import com.api.renascer.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJPARepository userJPARepository;

    @Override
    public Optional<User> findById(Long id) {
        return userJPARepository.findById(id);
    }

    @Override
    public UserDetails findByLogin(String login) {
        return userJPARepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return userJPARepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userJPARepository.deleteById(id);
    }

    @Override
    public List<User> findAllBirthdays() {
        return userJPARepository.findAllBirthdays();
    }

    @Override
    public List<String> findAllExpoToken() {
        return userJPARepository.findAllExpoToken();
    }
}
