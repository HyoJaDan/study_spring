package com.OAuthSession3.OAuthSession.dto;

public interface OAuth2Response {
    // 1. 제공자 (Ex. naver, google, ...)
    String getProvider();
    // 2. 제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    // 3. 이메일
    String getEmail();
    // 4. 사용자 실명 (설정한 이름)
    String getName();
}
