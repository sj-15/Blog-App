package com.test.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Blog.dto.PostDTO;
import com.test.Blog.service.GlobalService;
import com.test.Blog.util.EntitiyHawk;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class GlobalController extends EntitiyHawk {
	@Autowired
	private GlobalService gs;
	
	@Autowired
    private HttpServletRequest request;
	
	@PostMapping("/publish")
	public String publish(@RequestBody PostDTO postDTO) {
		return gs.publish(postDTO, request);
	}
}
