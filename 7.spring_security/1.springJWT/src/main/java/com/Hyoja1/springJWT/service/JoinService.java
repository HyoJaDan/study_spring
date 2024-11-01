package com.Hyoja1.springJWT.service;

import com.Hyoja1.springJWT.Repository.UserRepository;
import com.Hyoja1.springJWT.domain.User;
import com.Hyoja1.springJWT.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean joinProcess(JoinDTO joinDTO){
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);
        if(isExist){
            return false;
        }

        User user = new User();

        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);

        return true;
    }
}
