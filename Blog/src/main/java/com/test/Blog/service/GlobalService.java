package com.test.Blog.service;

import java.util.List;

import com.test.Blog.dto.PostDTO;
import com.test.Blog.dto.UpdatePostDTO;
import com.test.Blog.models.Posts;

import jakarta.servlet.http.HttpServletRequest;

public interface GlobalService {
	public String publish(PostDTO pDto, HttpServletRequest request);
	public List<Posts> getAllPosts();
	public long getPostCount();
	public List<Posts> getPostByUser(int published_by);
	public String updatePost(UpdatePostDTO updatePostDTO, HttpServletRequest request);
}
