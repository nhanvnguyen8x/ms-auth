package com.ms.rest.base.controller;

import com.ms.rest.base.api.dto.auth.AuthRequest;
import com.ms.rest.base.api.dto.auth.AuthResponse;
import com.ms.rest.base.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse response = authService.handleLogin(authRequest);
        return ResponseEntity.ok(response);
    }
}
