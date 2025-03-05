package com.test.Blog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
public class LoginDto {

    @NotBlank(message = "should not be empty")
    @Length(max = 25)
    @Valid
    private String email;
    @NotBlank(message = "should not be empty")
    @Length(max = 25)
    @Valid
    private String password;

    public LoginDto(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
