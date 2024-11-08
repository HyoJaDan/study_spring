package com.OAuthSession3.OAuthSession.service;

import com.OAuthSession3.OAuthSession.dto.CustomOAuth2User;
import com.OAuthSession3.OAuthSession.dto.GoogleResponse;
import com.OAuthSession3.OAuthSession.dto.NaverResponse;
import com.OAuthSession3.OAuthSession.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService OAuth2UserService의 구현체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        // 1. 네이버인지 구글인지 어떤 플랫폼인지 받아온다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 2. 3번을 받기 위해 DTO를 생성.
        OAuth2Response oAuth2Response = null;

        // 3. 이렇게 네이버와 구글을 나누는 이유는 네이버에서 보내주는 규격과 구글에서 보내주는 규격이 다르기 때문이다.
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else return null;

        String role = "ROLE_USER";
        return new CustomOAuth2User(oAuth2Response, role);
    }
}