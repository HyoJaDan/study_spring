package com.jun4.OAuth.JWT.repository;


import com.jun4.OAuth.JWT.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}