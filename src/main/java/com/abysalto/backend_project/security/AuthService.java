package com.abysalto.backend_project.security;

import com.abysalto.backend_project.security.dto.LoginRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtEncoder jwtEncoder;

    public String login(LoginRequest request) {

        RestClient restClient = RestClient.create("https://dummyjson.com");
        
        try {
            restClient.post()
                    .uri("/auth/login")
                    .body(request)
                    .retrieve()
                    .body(Object.class);
        } catch (Exception e) {
            log.error("Login failed for user: {}", request.getUsername());
            throw new RuntimeException("Invalid credentials");
        }

        log.info("User logged in: {}", request.getUsername());
        return generateToken(request.getUsername());
    }

    private String generateToken(String username) {
        Instant now = Instant.now();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("backend-project")
                .issuedAt(now)
                .expiresAt(now.plus(15, ChronoUnit.MINUTES))
                .subject(username)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}