package com.susancodes.rest_api_blog_application.service;

import com.susancodes.rest_api_blog_application.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
