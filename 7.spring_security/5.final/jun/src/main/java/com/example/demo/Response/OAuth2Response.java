package com.example.demo.Response;

import com.example.demo.domain.AuthProvider;

public interface OAuth2Response {
    AuthProvider getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
