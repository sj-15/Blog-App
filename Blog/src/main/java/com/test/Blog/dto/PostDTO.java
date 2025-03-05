package com.test.Blog.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class PostDTO {

    @NotBlank(message = "should not be empty")
    @Length(max = 450)
    @Valid
    String title;
    @NotBlank(message = "should not be empty")
    @Length(max = 5000)
    @Valid
    String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
