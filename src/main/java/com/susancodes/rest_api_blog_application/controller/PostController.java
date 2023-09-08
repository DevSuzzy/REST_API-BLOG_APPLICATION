package com.susancodes.rest_api_blog_application.controller;

import com.susancodes.rest_api_blog_application.payload.PostDto;
import com.susancodes.rest_api_blog_application.payload.PostResponse;
import com.susancodes.rest_api_blog_application.repository.PostRepository;
import com.susancodes.rest_api_blog_application.service.PostService;
import com.susancodes.rest_api_blog_application.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService,
                          PostRepository postRepository) {
        this.postService = postService;

        this.postRepository = postRepository;
    }
    // create blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    // get all post rest api

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy, sortDir);

    }
    // get post by id

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
         return ResponseEntity.ok(postService.getPostById(id));
    }
    // update post by id api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id")long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);

    }
    //delete post by id api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return  new ResponseEntity<>("Post deleted successfully.",  HttpStatus.OK);

    }

}
