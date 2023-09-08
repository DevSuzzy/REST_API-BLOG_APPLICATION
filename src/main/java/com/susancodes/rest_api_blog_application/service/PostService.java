package com.susancodes.rest_api_blog_application.service;

import com.susancodes.rest_api_blog_application.entity.Post;
import com.susancodes.rest_api_blog_application.payload.PostDto;
import com.susancodes.rest_api_blog_application.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost (PostDto postDto, long id);

    void deletePostById (long id);
}

