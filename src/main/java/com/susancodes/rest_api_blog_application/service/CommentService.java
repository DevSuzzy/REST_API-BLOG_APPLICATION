package com.susancodes.rest_api_blog_application.service;

import com.susancodes.rest_api_blog_application.entity.Comment;
import com.susancodes.rest_api_blog_application.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> findByPostId(long postid);
}
