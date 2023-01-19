package com.blogrestapi.demo.service;

import com.blogrestapi.demo.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(Long postId, CommentDto commentDto) ;

    public List<CommentDto> getCommentsByPostId(Long postId) ;

    public CommentDto getCommentById(Long postId, Long commentId) ;

    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) ;
}
