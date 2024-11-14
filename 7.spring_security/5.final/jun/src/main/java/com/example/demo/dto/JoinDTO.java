package com.example.demo.dto;

import com.example.demo.domain.Role;
import lombok.Data;

@Data
public class JoinDTO {
    private String username;
    private String password;
    private Role role;
}
