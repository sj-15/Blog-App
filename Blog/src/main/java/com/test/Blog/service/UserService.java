package com.test.Blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.test.Blog.dto.LoginDto;
import com.test.Blog.dto.RegisterUserDTO;

public interface UserService extends UserDetailsService{
    public String register(RegisterUserDTO rUserDTO);
    public String login(LoginDto loginDto);
	public UserDetails loadUserByUsername(String email);
}
