package com.abysalto.backend_project.security.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
}