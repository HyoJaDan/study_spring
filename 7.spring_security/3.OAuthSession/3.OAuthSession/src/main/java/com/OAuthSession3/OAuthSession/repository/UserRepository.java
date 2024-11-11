package com.OAuthSession3.OAuthSession.repository;

import com.OAuthSession3.OAuthSession.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}