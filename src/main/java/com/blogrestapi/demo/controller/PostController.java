package com.blogrestapi.demo.controller;

import com.blogrestapi.demo.payload.PostDto;
import com.blogrestapi.demo.service.PostService;
import com.blogrestapi.demo.utils.Constants;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired private PostService postService ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        PostDto response = new PostDto() ;
        response = postService.createPost(postDto) ;
        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(
            @RequestParam( value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam( value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam( value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam( value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return  ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable( name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id)) ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePost(postDto, id)) ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post for the given id is deleted", HttpStatus.OK) ;
    }

}
