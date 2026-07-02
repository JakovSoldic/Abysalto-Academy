package com.abysalto.backend_project.security.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}