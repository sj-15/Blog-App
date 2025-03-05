package com.test.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Blog.dto.PostDTO;
import com.test.Blog.dto.UpdatePostDTO;
import com.test.Blog.service.GlobalService;
import com.test.Blog.util.EntitiyHawk;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class GlobalController{
	
	
	private final EntitiyHawk entitiyHawk;
	
	public GlobalController(EntitiyHawk entitiyHawk) {
		super();
		this.entitiyHawk = entitiyHawk;
	}

	@Autowired
	private GlobalService gs;
	
	@Autowired
    private HttpServletRequest request;
	
	@PostMapping("/publish")
	public ResponseEntity<?> publish(@RequestBody PostDTO postDTO) {
		return entitiyHawk.genericResponse(gs.publish(postDTO, request));
	}
	
	@GetMapping("/getPost")
	public ResponseEntity<?> getAllPost() {
		return entitiyHawk.genericSuccess(gs.getAllPosts());
	}
	
	@GetMapping("/getPostCount")
	public ResponseEntity<?> getPostCount(){
		return entitiyHawk.genericResponse(gs.getPostCount());
	}
	
	@GetMapping("/getPostByUser/{published_by}")
	public ResponseEntity<?> getPostByUser(@PathVariable int published_by){
		return entitiyHawk.genericResponse(gs.getPostByUser(published_by));
	}
	
	@PostMapping("/updatePost")
	public ResponseEntity<?> updatePost(@RequestBody UpdatePostDTO updatePostDTO){
		return entitiyHawk.genericResponse(gs.updatePost(updatePostDTO, request));
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<?> getPostById(@PathVariable("postId") int post_id){
		return entitiyHawk.genericResponse(gs.getPostById(post_id));
	}
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId){
		return entitiyHawk.genericResponse(gs.deletePost(postId, request));
	}
}
