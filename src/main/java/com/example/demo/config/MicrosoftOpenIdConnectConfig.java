package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class MicrosoftOpenIdConnectConfig {

    @Value("${microsoft.client-id}")
    private String clientId;

    @Value("${microsoft.client-secret}")
    private String clientSecret;

    @Value("${microsoft.access-token-uri}")
    private String accessTokenUri;

    @Value("${microsoft.user-authorization-uri}")
    private String userAuthorizationUri;

    @Value("${microsoft.redirect-uri}")
    private String redirectUri;

    @Bean
    public OAuth2ProtectedResourceDetails microsoftOpenId() {
        final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();

        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("openid", "profile", "offline_access", "User.Read", "Mail.Read", "Contacts.Read"));
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setUseCurrentUri(false);

        return details;
    }

    @Bean
    public OAuth2RestTemplate restTemplate(final OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(microsoftOpenId(), clientContext);
    }
}
