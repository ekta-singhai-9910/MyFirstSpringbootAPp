package com.blogrestapi.demo.repository;

import com.blogrestapi.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDataRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId) ;
}
