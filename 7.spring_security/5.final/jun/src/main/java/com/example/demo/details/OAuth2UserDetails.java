package com.example.demo.details;

import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2UserDetails implements OAuth2User {
    private final UserDTO userDTO;
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(userDTO.getRole().name()));
        return collection;
    }
    @Override
    public String getName() {
        return userDTO.getName();
    }
    public String getUsername() {
        return userDTO.getUsername();
    }

}