package com.ms.rest.base.service.auth;

import com.ms.rest.base.api.dto.auth.AuthRequest;
import com.ms.rest.base.api.dto.auth.AuthResponse;
import com.ms.rest.base.exception.BadRequestException;
import com.ms.rest.base.domain.Account;
import com.ms.rest.base.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final TokenService tokenService;

    public AuthResponse handleLogin(AuthRequest authRequest) {
        Account account = accountRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("Incorrect username"));

        if (!passwordEncoder.matches(authRequest.getPassword(), account.getHashedPassword())) {
            throw new BadRequestException("Incorrect password");
        }

        AuthResponse authResponse = new AuthResponse();
        String accessToken = tokenService.generateAccessToken(account);
        authResponse.setAccessToken(accessToken);

        return authResponse;
    }

}
