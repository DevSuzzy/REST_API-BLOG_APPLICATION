package com.susancodes.rest_api_blog_application.controller;

import com.susancodes.rest_api_blog_application.entity.Comment;
import com.susancodes.rest_api_blog_application.payload.CommentDto;
import com.susancodes.rest_api_blog_application.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap1/")

public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }
    @PostMapping("/posts{postId}/comments")
    public ResponseEntity<CommentDto> createComment (@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){
return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
}
