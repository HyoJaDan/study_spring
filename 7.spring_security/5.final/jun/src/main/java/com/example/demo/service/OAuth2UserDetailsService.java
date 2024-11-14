package com.example.demo.service;

import com.example.demo.Response.GoogleResponse;
import com.example.demo.Response.NaverResponse;
import com.example.demo.Response.OAuth2Response;
import com.example.demo.details.OAuth2UserDetails;
import com.example.demo.domain.Role;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.*;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else return null;

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            UserEntity userEntity = UserEntity.oauthUserBuilder()
                    .provider(oAuth2Response.getProvider())
                    .email(oAuth2Response.getEmail())
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(userEntity);

            UserDTO userDTO = UserDTO.builder()
                    .role(Role.ROLE_USER)
                    .username(username)
                    .name(oAuth2Response.getName())
                    .build();

            return new OAuth2UserDetails(userDTO);
        }
        else {
            user.setEmail(oAuth2Response.getEmail());
            user.setName(oAuth2Response.getName());

            userRepository.save(user);
            UserDTO userDTO = UserDTO.builder()
                    .role(user.getRole())
                    .username(user.getUsername())
                    .name(oAuth2Response.getName())
                    .build();

            return new OAuth2UserDetails(userDTO);
        }
    }
}