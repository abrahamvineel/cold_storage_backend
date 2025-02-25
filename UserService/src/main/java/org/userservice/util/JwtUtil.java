package org.userservice.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;
import org.userservice.service.SessionsService;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "YourSuperSecretKeyForJwtYourSuperSecretKeyForJwt";
    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    private final long EXPIRATION_TIME = 1000 * 60 * 60;
    private final SessionsService sessionsService;

    @Inject
    public JwtUtil(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    public String generateToken(String email) {
        Date expiry = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(expiry)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
        sessionsService.saveToken(email, token, expiry);
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
