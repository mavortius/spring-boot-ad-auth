package com.example.demo.controller;

import com.example.demo.service.UserProfile;
import com.example.demo.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserProfileService profileService;

    public HomeController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<UserProfile> home() {
        return Optional.ofNullable(profileService.getUserProfile())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
