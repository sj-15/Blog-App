package com.test.Blog.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.test.Blog.models.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
    
    @Value("${jwt.secret}")  // Read from application.properties
    private String secretKey;

    public String createJWTToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("user_id", user.getUserId());

        SecretKey key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("MY Blog")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
