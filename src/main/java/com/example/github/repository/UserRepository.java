package com.example.github.repository;

import com.example.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from users u where u.name = ?1")
    Optional<User> findByName(String name);

    @Query("select u from users u where u.email = ?1")
    Optional<User> findUserByEmail(String email);
}
