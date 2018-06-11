package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final OAuth2RestOperations restTemplate;

    @Value("${microsoft.user-info-uri}")
    private String baseUrl;

    public UserProfileService(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserProfile getUserProfile() {
        UserProfile profile = restTemplate.getForObject(baseUrl, UserProfile.class);

        return profile;
    }


}
