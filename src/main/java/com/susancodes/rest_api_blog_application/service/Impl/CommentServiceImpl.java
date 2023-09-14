package com.susancodes.rest_api_blog_application.service.Impl;
import com.susancodes.rest_api_blog_application.entity.Comment;
import com.susancodes.rest_api_blog_application.entity.Post;
import com.susancodes.rest_api_blog_application.exception.BlogApiException;
import com.susancodes.rest_api_blog_application.exception.ResourceNotFoundException;
import com.susancodes.rest_api_blog_application.payload.CommentDto;
import com.susancodes.rest_api_blog_application.repository.CommentRepository;
import com.susancodes.rest_api_blog_application.repository.PostRepository;
import com.susancodes.rest_api_blog_application.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
private final ModelMapper mapper;
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
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

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        // retrieve comment by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updatedcomment = commentRepository.save(comment);
        return mapToDTO(updatedcomment);


    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // delete comment by id
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }


    //  convert Entity to DTO
    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        //CommentDto commentDto = new CommentDto();
       // commentDto.setId(comment.getId());
       // commentDto.setName(comment.getName());
        //commentDto.setEmail(comment.getEmail());
       // commentDto.setBody(comment.getBody());
        return commentDto;
    }
// convert  DTO to Entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
       // Comment comment = new Comment();
       // comment.setId(commentDto.getId());
        //comment.setName(commentDto.getName());
        //comment.setEmail(commentDto.getEmail());
        //comment.setBody(commentDto.getBody());
        return comment;
    }
}
