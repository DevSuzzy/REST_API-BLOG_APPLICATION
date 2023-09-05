package com.susancodes.rest_api_blog_application.repository;

import com.susancodes.rest_api_blog_application.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
