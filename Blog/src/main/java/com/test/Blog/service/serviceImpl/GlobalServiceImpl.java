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
import com.test.Blog.dto.UpdatePostDTO;
import com.test.Blog.models.Posts;
import com.test.Blog.models.Users;
import com.test.Blog.repository.PostRepository;
import com.test.Blog.repository.UserRepository;
import com.test.Blog.service.GlobalService;
import java.awt.PrintGraphics;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        post.setCreatedOn(getFormattedDate(new Date()));
        post.setUpdatedOn(getFormattedDate(new Date()));
        post.setIsDeleted(false);

        postRepository.save(post);
        return "Post published successfully!";
    }
    
    @Override
    public List<Posts> getAllPosts(){
    	return postRepository.findAll();
    }
    
    @Override
    public long getPostCount() {
    	return postRepository.count();
    }
    
    @Override
    public List<Posts> getPostByUser(int published_by){
    	return postRepository.findByPublishedBy_UserId(published_by);
    }
    
    @Override 
    public String updatePost(UpdatePostDTO updatePostDTO, HttpServletRequest request) {
    	int userId = (int) request.getAttribute("user_id");
    	
    	Optional<Posts> optionalPost = postRepository.findById(updatePostDTO.getPost_id());
    	
    	if (optionalPost.isEmpty()) {
            return "Post not found!";
        }

        Posts post = optionalPost.get();

        // Ensure that only the author can update the post
        if (post.getPublishedBy().getUserId() != userId) {
            return "You are not authorized to update this post!";
        }
        
        post.setPostTitle(updatePostDTO.getTitle());
        post.setPostBody(updatePostDTO.getBody());
        
        postRepository.save(post);
    	return "Post updated successfully!";
    }
    
    @Override
    public Posts getPostById(Integer postId) {
        return postRepository.findById(postId).orElse(null);
    }
    
    @Override
    public String deletePost(Integer postId, HttpServletRequest request) {
    	int userId = (int) request.getAttribute("user_id");
    	
    	Optional<Posts> optionalPost = postRepository.findById(postId);
    	
    	if (optionalPost.isEmpty()) {
            return "Post not found!";
        }

        Posts post = optionalPost.get();

        // Ensure that only the author can update the post
        if (post.getPublishedBy().getUserId() != userId) {
            return "You are not authorized to update this post!";
        }
        
        postRepository.deleteById(postId);
    	return "Post deleted successfully!";
    }
    
    private Date getFormattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        String formattedDate = sdf.format(new Date()); // Format the date as a String

        // Convert formatted string back to Date
        Date finalDate = null;
		try {
			finalDate = sdf.parse(formattedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return finalDate;
        
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
