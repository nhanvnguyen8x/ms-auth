package com.ms.rest.base.service.auth;

import com.ms.rest.base.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class TokenService {
    private static final String SECRET_STRING = "VZ3mFa4IeHw5Z3GtAZKauyT4L2bqLp0f3nY7BgcWFZo=";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_STRING));

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 30;             // 30 Minutes
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 30 ; // 30 days

    private SecretKey getSigningKey() {
        return SECRET_KEY;
    }

    public String generateAccessToken(Account account) {
        return Jwts.builder()
                .claims(buildClaims(account))
                .subject(String.valueOf(account.getId()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(Account account) {
        return Jwts.builder()
                .claims(buildClaims(account))
                .subject(String.valueOf(account.getId()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public Long extractAccountId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

    private Map<String, String> buildClaims(Account account) {
        Map<String, String> claims = new HashMap<>();
        claims.put("id", String.valueOf(account.getId()));
        claims.put("username", account.getUsername());
        claims.put("email", account.getEmail());

        return claims;
    }

}
