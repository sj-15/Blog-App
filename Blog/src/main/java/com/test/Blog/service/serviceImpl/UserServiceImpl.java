package com.test.Blog.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.Blog.dto.LoginDto;
import com.test.Blog.dto.RegisterUserDTO;
import com.test.Blog.models.Users;
import com.test.Blog.repository.UserRepository;
import com.test.Blog.service.UserService;
import com.test.Blog.util.JWTUtils;

@Service
public class UserServiceImpl  implements UserService {
	private final JWTUtils jwtUtils;

    @Autowired
    public UserServiceImpl(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Autowired
    private UserRepository ur;

    @Override
    public String register(RegisterUserDTO rUserDTO){
        Users user = new Users();
        user.setUserName(rUserDTO.getName());
        user.setEmail(rUserDTO.getEmail());
        user.setPassword(rUserDTO.getPassword());
        ur.save(user);
        return "User Registerd Successfully!";
    }

    @Override
    public String login(LoginDto loginDto){
        Optional<Users> userOpt = ur.findByEmail(loginDto.getEmail());
        if(userOpt.isPresent() && userOpt.get().getPassword().equals(loginDto.getPassword())){
            return jwtUtils.createJWTToken(userOpt.get());
        }
        return "Invalid credentials!";
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ur.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
