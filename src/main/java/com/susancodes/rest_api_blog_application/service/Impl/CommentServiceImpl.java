package com.susancodes.rest_api_blog_application.service.Impl;
import com.susancodes.rest_api_blog_application.entity.Comment;
import com.susancodes.rest_api_blog_application.entity.Post;
import com.susancodes.rest_api_blog_application.exception.ResourceNotFoundException;
import com.susancodes.rest_api_blog_application.payload.CommentDto;
import com.susancodes.rest_api_blog_application.repository.CommentRepository;
import com.susancodes.rest_api_blog_application.repository.PostRepository;
import com.susancodes.rest_api_blog_application.service.CommentService;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements CommentService {
    private  CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity (commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // save comment entity
        Comment newcomment = commentRepository.save(comment);

        return mapToDTO(newcomment);
    }

    //  convert Entity to DTO
    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
// convert  DTO to Entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
