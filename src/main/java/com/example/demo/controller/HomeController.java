package com.example.demo.controller;

import com.example.demo.service.UserProfile;
import com.example.demo.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
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
    public ResponseEntity<String> home() {
        UserProfile user = profileService.getUserProfile();
        String hello = "Olá, " + user.getGivenName() + ", seu email é " + user.getUserPrincipalName();

        return ResponseEntity.ok(hello);
    }

    @GetMapping("/microsoft-logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.invalidate();

        return ResponseEntity.ok("Você saiu");
    }

}
