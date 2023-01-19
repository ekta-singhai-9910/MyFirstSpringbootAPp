package com.blogrestapi.demo.service;

import com.blogrestapi.demo.payload.PostDto;
import com.blogrestapi.demo.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto) ;

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) ;

    PostDto getPostById(long id) ;

    PostDto updatePost(PostDto postDto, long id) ;

    void deletePostById(long id) ;
}
