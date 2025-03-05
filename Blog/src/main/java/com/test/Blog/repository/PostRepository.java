package com.test.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Blog.models.Posts;

public interface PostRepository extends JpaRepository<Posts,Integer> {

  
}
