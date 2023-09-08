package com.susancodes.rest_api_blog_application.service;

import com.susancodes.rest_api_blog_application.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
