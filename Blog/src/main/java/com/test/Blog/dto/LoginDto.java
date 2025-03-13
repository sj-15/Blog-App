package com.test.Blog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "should not be empty")
    @Length(max = 25)
    @Valid
    private String email;
    @NotBlank(message = "should not be empty")
    @Length(max = 25)
    @Valid
    private String password;

}
