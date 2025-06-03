package com.api.renascer.domain.service;

import com.api.renascer.domain.dto.ChangePassword;
import com.api.renascer.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails findByLogin(String login);

    User load(Long id);

    User save(User user);

    String deleteById(Long id);

    String alterPassword(ChangePassword changePassword);

    List<User> findAllBirthdays();

    List<String> findAllExpoToken();
}
