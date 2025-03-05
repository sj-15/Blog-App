package com.test.Blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Blog.models.Posts;

public interface PostRepository extends JpaRepository<Posts,Integer> {
	public List<Posts> findByPublishedBy_UserId(int userId);
  
}
