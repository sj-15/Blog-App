package com.test.Blog.dto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
@Setter
@Getter
public class UpdatePostDTO {
    
    @Length(max = 450)
    @Valid
    String title;
    @Length(max = 5000)
    @Valid
    String body;
    @Valid
    Integer post_id;

}