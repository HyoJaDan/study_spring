package com.OAuthSession3.OAuthSession.service;

import com.OAuthSession3.OAuthSession.domain.UserEntity;
import com.OAuthSession3.OAuthSession.dto.CustomOAuth2User;
import com.OAuthSession3.OAuthSession.dto.GoogleResponse;
import com.OAuthSession3.OAuthSession.dto.NaverResponse;
import com.OAuthSession3.OAuthSession.dto.OAuth2Response;
import com.OAuthSession3.OAuthSession.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

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

        // 1. 어느 소셜 로그인(google | naver)에서 받았는지 확인하는 getProvider, user에 대한 번호 데이터 ID를 받음
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        // 2. 1번에서 받은 id를 가지고 repository에서 데이터 확인
        UserEntity existData = userRepository.findByUsername(username);

        // 3. 처음 회원가입한 유저라면
        if (existData == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);
        }
        else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);
        }

        String role = "ROLE_USER";
        return new CustomOAuth2User(oAuth2Response, role);
    }
}