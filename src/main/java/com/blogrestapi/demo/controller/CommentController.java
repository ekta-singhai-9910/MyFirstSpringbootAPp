package com.blogrestapi.demo.controller;

import com.blogrestapi.demo.payload.CommentDto;
import com.blogrestapi.demo.repository.CommentDataRepository;
import com.blogrestapi.demo.repository.PostDataRepository;
import com.blogrestapi.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts/")
public class CommentController {

    @Autowired
    CommentService commentService ;


    @PostMapping("{postId}/comments")
    public ResponseEntity<?> createComment( @PathVariable(name = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED) ;
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable(name = "postId") long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId)) ;
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable( name = "postId") long postId, @PathVariable(name = "commentId") long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId)) ;
    }

    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "postId") long postId, @PathVariable(name = "commentId") long commentId, @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto)) ;
    }
}
