package com.blogrestapi.demo.repository;

import com.blogrestapi.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email) ;

    Optional<User> findByEmailOrUsername(String email, String username) ;

    Optional<User> findByUsername(String username) ;

    Boolean existsByUsername(String username) ;

    Boolean existsByEmail(String email) ;
}
