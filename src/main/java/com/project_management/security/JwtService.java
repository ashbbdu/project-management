package com.project_management.security;

//├── Generate Token
//├── Validate Token
//└── Extract Claims

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

//    convert Stromg secret key to Key because JWT accepts key not String
//    So our helper method will convert the string into a cryptographic key.
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken (UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

//
    public Claims extractAllClaims (String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractSubject (String token) {
        return extractAllClaims(token).getSubject();
    }


    // 5. Extract expiration
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }


//    checks if the token is expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = extractSubject(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token); // we can add other filters as well like password changed , roles changed etc
    }
}
