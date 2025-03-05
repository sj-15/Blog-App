package com.test.Blog.service;

import com.test.Blog.dto.PostDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface GlobalService {
	public String publish(PostDTO pDto, HttpServletRequest request);
}
