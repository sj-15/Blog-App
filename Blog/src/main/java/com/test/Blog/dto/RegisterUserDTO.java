package com.test.Blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
@Setter
@Getter
@NoArgsConstructor
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

    @Override
	public String toString() {
		return "RegisterUserDTO [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
}
