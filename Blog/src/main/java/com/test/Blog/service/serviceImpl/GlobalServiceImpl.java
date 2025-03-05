package com.test.Blog.service.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.Blog.dto.PostDTO;
import com.test.Blog.models.Posts;
import com.test.Blog.models.Users;
import com.test.Blog.repository.PostRepository;
import com.test.Blog.repository.UserRepository;
import com.test.Blog.service.GlobalService;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class GlobalServiceImpl implements GlobalService {

    private static final String SECRET = "Ue9Lx@kGv9LmH#bDz8W!fXnC2PqYtVJ3"; // Should be at least 32 characters

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String publish(PostDTO pDto, HttpServletRequest request) {
        String token = extractJwtFromRequest(request);
        if (token == null) {
            return "Unauthorized: JWT token missing!";
        }

        Integer userId = getUserIdFromJwt(token);
        if (userId == null) {
            return "Unauthorized: Invalid token!";
        }

        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found!";
        }

        Posts post = new Posts();
        post.setPostTitle(pDto.getTitle());
        post.setPostBody(pDto.getBody());
        post.setPublishedBy(user);
        post.setCreatedOn(new Date());
        post.setUpdatedOn(new Date());
        post.setIsDeleted(false);

        postRepository.save(post);
        return "Post published successfully!";
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Integer getUserIdFromJwt(String token) {
        // ✅ Convert the String secret to SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        try {
            Claims claims = Jwts.parser()
                .verifyWith(secretKey)  // Correct way to verify with secret key
                .build()                 // Required in JJWT 0.12.x
                .parseSignedClaims(token) // New method replacing parseClaimsJws()
                .getPayload(); 

            return claims.get("user_id", Integer.class); // Extract user_id correctly
        } catch (Exception e) {
            return null;
        }
    }
}
