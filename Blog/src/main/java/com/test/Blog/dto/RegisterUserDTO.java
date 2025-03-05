package com.test.Blog.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
public class RegisterUserDTO {

    @NotBlank(message = "Email cannot be blank")
    @Length(max = 40)
    @Valid
    private String email;
    @NotBlank(message = "Name cannot be blank")
    @Length(max = 40)
    @Valid
    private String name;
    @NotBlank(message = "password cannot be blank")
    @Length(min = 3, max = 45)
    @Valid
    private String password;

    public RegisterUserDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "RegisterUserDTO [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
}
