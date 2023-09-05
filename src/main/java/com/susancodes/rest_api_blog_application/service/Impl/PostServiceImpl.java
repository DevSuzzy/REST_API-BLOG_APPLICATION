package com.susancodes.rest_api_blog_application.service.Impl;

import com.susancodes.rest_api_blog_application.entity.Post;
import com.susancodes.rest_api_blog_application.payload.PostDto;
import com.susancodes.rest_api_blog_application.repository.PostRepository;
import com.susancodes.rest_api_blog_application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert Dto to Entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);

        // convert Entity to Dto
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
