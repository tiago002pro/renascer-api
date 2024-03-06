package com.api.renascer.user.repository;

import com.api.renascer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
    Optional<User> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.login = :email")
    User findByEmail(@Param("email") String email);

}
