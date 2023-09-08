package com.susancodes.rest_api_blog_application.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.susancodes.rest_api_blog_application.entity.Post;
import com.susancodes.rest_api_blog_application.exception.ResourceNotFoundException;
import com.susancodes.rest_api_blog_application.payload.PostDto;
import com.susancodes.rest_api_blog_application.payload.PostResponse;
import com.susancodes.rest_api_blog_application.repository.PostRepository;
import com.susancodes.rest_api_blog_application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {

//        // convert Dto to Entity

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        // convert Entity to Dto
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return  postResponse;
    }


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow (() -> new ResourceNotFoundException("Post", "id", id));
        return objectMapper.convertValue(post,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        // delete post by id
        Post post = postRepository.findById(id).orElseThrow (() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }

    // convert Entity to DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        post.setId(post.getId());
        post.setContent(post.getContent());
        post.setDescription(post.getTitle());
        post.setComments(post.getComments());
        return postDto;
    }
    // Convert DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;

    }

}
