package com.example.demo.dto;

import com.example.demo.domain.Role;
import lombok.*;

@Getter @Setter
@Builder
public class UserDTO {
    private Role role;
    private String name;
    private String username;
}