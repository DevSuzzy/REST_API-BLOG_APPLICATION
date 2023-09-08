package com.susancodes.rest_api_blog_application.repository;

import com.susancodes.rest_api_blog_application.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
