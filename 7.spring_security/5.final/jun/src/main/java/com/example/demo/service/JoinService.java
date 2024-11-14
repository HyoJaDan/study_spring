package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.JoinDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    public boolean joinProcess(JoinDTO joinDTO){
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        boolean isExist = userRepository.existsByUsername(username);
        if(isExist) return false;
        if (joinDTO.getRole() == null || !Role.isValidRole(joinDTO.getRole().name())) {
            return false;
        }
        UserEntity user = UserEntity.localUserBuilder()
                .username(username)
                .password(password)
                .role(joinDTO.getRole())
                .build();
        userRepository.save(user);

        return true;
    }
}
