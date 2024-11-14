package com.example.demo.Response;

import com.example.demo.domain.AuthProvider;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {
    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }
    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }
    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }
    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}