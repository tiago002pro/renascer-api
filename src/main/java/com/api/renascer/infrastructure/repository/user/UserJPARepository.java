package com.api.renascer.infrastructure.repository.user;

import com.api.renascer.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

    @Query(
            nativeQuery = true,
            value = """
                SELECT u.token_expo_notification 
                FROM users u 
                    INNER JOIN person p ON p.id = u.person_id 
                WHERE to_char(TO_DATE(p.date_birth, 'DD/MM/YYYY'), 'DD-MM') = to_char(now(), 'DD-MM')
            """
    )
    List<User> findAllBirthdays();

    @Query(nativeQuery = true, value = " SELECT u.token_expo_notification FROM users u ")
    List<String> findAllExpoToken();
}
