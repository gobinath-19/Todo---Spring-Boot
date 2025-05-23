package com.gobi.Todo.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY_STRING = "c3c97fd97799e114b5c5bd9b11609d37bfb55477bc28bad671e7aaeec78532fb7dc25ac64bf2d5d61533326c4bee1ec647e9f7afa79eda4eb611f5c6c6bda64df779e7965bcd432a7c1e35f5343a7291d036c7f356d6b892f37118f05072a48776c5ad88ca8e233f5d617a116bdbd69a27e655c197ece31cf359ba070dd106f52d31ec68c90bf2ca0b856cfd6016177b639158129c8d5caca03c23ebc0ab7bb87b12376e3b4b6b315f6a66bab4d44ff9b039d4e1f1d87b4c9b3fa0dc6c1c24d7b221f77445cbf523e39c2766a351b0e32ab072a076804d3b6c35c47637f969e6a775f3813330673a34d6625638a0b4176c40d2d3d4a3e1a2bcfedfcfbbc4f8ae";

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String extractUserID(String token){
        return extractClaims(token).getSubject(); // Use different claim if needed
    }
}
