package com.test.Blog.dto;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
public class UpdatePostDTO {
    
    @Length(max = 450)
    @Valid
    String title;
    @Length(max = 5000)
    @Valid
    String body;
    @Valid
    Integer post_id;

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

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