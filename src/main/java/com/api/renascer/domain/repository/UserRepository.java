package com.api.renascer.domain.repository;

import com.api.renascer.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    UserDetails findByLogin(String login);

    User save(User user);

    void deleteById(Long id);

    List<User> findAllBirthdays();

    List<String> findAllExpoToken();
}
