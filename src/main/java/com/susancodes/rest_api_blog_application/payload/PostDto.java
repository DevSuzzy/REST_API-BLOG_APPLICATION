package com.susancodes.rest_api_blog_application.payload;

import com.susancodes.rest_api_blog_application.entity.Comment;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;

}
