package com.OAuthSession3.OAuthSession.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class CustomClientRegistrationRepo {
    private final SocialClientRegistration socialClientRegistration;
    public ClientRegistrationRepository clientRegistrationRepository() {
        // 카카오, 구굴ㄷ,ㅇ 많은 경우가 있지 않으니 inmemory에 저장해도 된다.
        return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration(), socialClientRegistration.googleClientRegistration());
    }
}
