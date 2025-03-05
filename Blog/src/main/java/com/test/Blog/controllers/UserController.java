package com.test.Blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.Blog.dto.LoginDto;
import com.test.Blog.dto.RegisterUserDTO;
import com.test.Blog.service.UserService;
import com.test.Blog.util.EntitiyHawk;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController{
    
    @Autowired
    private EntitiyHawk entityHawk;
    

    @Autowired
    private UserService us;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO rUserDTO) {
        us.register(rUserDTO);
        return entityHawk.genericSuccess(rUserDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return us.login(loginDto);
    }

}
