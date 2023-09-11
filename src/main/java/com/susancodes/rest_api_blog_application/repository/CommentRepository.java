package com.susancodes.rest_api_blog_application.repository;

import com.susancodes.rest_api_blog_application.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPostId(long postId);
}
